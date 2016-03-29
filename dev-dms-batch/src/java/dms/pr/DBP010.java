package dms.pr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : DMS-BI/기준정보</li>
 * <li>서브 업무명 : BEDU001</li>
 * <li>설  명 : <pre>[PR]색상생산업체코드 동기화</pre></li>
 * <li>작성일 : 2015-08-04</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 */
public class DBP010 extends AbsBatchComponent {
    private int processCnt = 0;
    private String taskNo = "";
    private String procFileName = "";
	
    /**
     * 배치 생성자. 
     * 상위클래스 생성자 호출
     */
    public DBP010() {
		super();
	}

	
    /**
     * 배치 전처리 메소드. 
     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
     */
    public void beforeExecute(IBatchContext context) {
        Log log = getLog(context);
        
        processCnt = 0;
        taskNo = "";
        procFileName = "";
        
        IOnlineContext    onlineCtx  = makeOnlineContext(context);
        IDataSet reqDS = new DataSet();
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq", reqDS, onlineCtx);
        taskNo = resDS.getField("TASK_NO");
        
        reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
        reqDS.putField("TASK_ID", context.getInParameter("TASK_ID"));
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("TASK_NM", context.getInParameter("TASK_NM"));
        reqDS.putField("GRP_ID", "BI");
        reqDS.putField("INST_CD", "DMS");
        reqDS.putField("BAT_TASK_PROC_ST_CD", "B");
        reqDS.putField("PROC_CNT", "0");
        reqDS.putField("FS_REG_USER_ID", "BAT");
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        
        callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS, onlineCtx);

        log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }

    /**
     * 배치 메인 메소드
     */
	public void execute(final IBatchContext context) {
        // 트랜잭션 시작
    	txBegin();  
    	dbStartSession();
    	dbBeginBatch();
    	
    	Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("PROC_DT", context.getInParameter("PROC_DT"));    //처리일
    	
    	dbSelect("SColorMfactCd", paramMap, makeRecordHandler(context), context); //단말기모델IF조회
        	
        dbSelect("SColorMfactCd", paramMap, makeRecordHandlerUpdate(context), context); //단말기모델IF조회
		
		// 트랜잭션 커밋
		dbEndBatch();
		dbEndSession();
		txCommit(); 
	}
	
	/**
	 * 배치 후처리 메소드. 
	 * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
	 */
    public void afterExecute(IBatchContext context) {
        IOnlineContext    onlineCtx  = makeOnlineContext(context);
        IDataSet reqDS = new DataSet();
        reqDS.putField("TASK_NO", taskNo);
        reqDS.putField("PROC_FILE_NM", procFileName);
        reqDS.putField("LS_CHG_USER_ID", "BAT");
        if (super.exceptionInExecute == null) {
            // execute() 정상인 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
        }else {
            // execute() 에서 에러 발생할 경우
            reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
            processCnt = 0;
        }
        reqDS.putField("PROC_CNT", ""+processCnt);
        IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst", reqDS, onlineCtx);

        Log log = getLog(context);
        if(log.isDebugEnabled()) {
            log.debug("공유컴포넌트 호출 결과:");
            log.debug(resDS);
        }
    }
    
    /**
     * 단말기모델IF조회 후 레코드 단위로 단말기모델 테이블에 입력
     * 
     */
    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
    	AbsRecordHandler rh = new AbsRecordHandler(context) {
			
			@Override
			public void handleRecord(IRecord row) {
				context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
				context.getLogger().debug("########### : " + row);
				
				String procClCd = row.get("CD_CL");
				// 개발계는 UKey 코드값이 1, 2로 넘어옴.
				// 운영계는 UKey 코드값이 01, 02로 넘어옴.
                //if ("1".equals(procClCd) || "01".equals(procClCd)) {
                if ("01".equals(procClCd)) {
                    row.set("CM_GRP_CD_ID", "DMS100");
                } else {
                    row.set("CM_GRP_CD_ID", "DMS101");
                }
                
                // UKey 인터페이스 Data가 신규만 와야 하는데 기존것도 같이 오는경우 처리로직 추가(기존에 처리된 자료인지 확인 후 Skip)
                int agnOrgCnt1 = Integer.parseInt(row.get("CM_CD_100_CNT"));
                int agnOrgCnt2 = Integer.parseInt(row.get("CM_CD_101_CNT"));
                
                if (("01".equals(procClCd) && agnOrgCnt1 == 0) || ("02".equals(procClCd) && agnOrgCnt2 == 0)) {
                	dbInsert("ICmCd", row, context);
                }
				processCnt++;
			}
		};
    	return rh;
    }
    
    /**
     * 단말기모델IF조회 후 레코드 단위로 단말기모델IF PROC_ST_CD 업데이트
     * 
     */
    public AbsRecordHandler makeRecordHandlerUpdate(IBatchContext context) {
        AbsRecordHandler rh = new AbsRecordHandler(context) {
            
            @Override
            public void handleRecord(IRecord row) {
                context.setProgressCurrent(getCurrentRecordCount()); // 진행률 표시
                context.getLogger().debug("########### : " + row);
                
                dbUpdate("UColorMfactCd", row, context);
            }
        };
        return rh;
    }

}