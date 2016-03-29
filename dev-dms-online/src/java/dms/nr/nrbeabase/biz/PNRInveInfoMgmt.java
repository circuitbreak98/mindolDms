package dms.nr.nrbeabase.biz;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]단말자산현황</li>
 * <li>설  명 : <pre>단말자산현황PU</pre></li>
 * <li>작성일 : 2015-07-17 15:48:23</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class PNRInveInfoMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRInveInfoMgmt(){
		super();
	}

	/**
	 * <pre>단말자산현황조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [단말기 코드]
	 *	- field : RENTAL_CNTRT_STA_DT [계약기간시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약기간종료일]
	 *	- field : OP_TYP_CD [계약상태]
	 *	- field : INVE_ST_CD [단말상태]
	 *	- field : ASSET_SLIP_ST [전표상태]
	 *	- field : EQP_SER_NO [일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_REQ_IN
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_MDL_NM [단말기 모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : RENTAL_CNTRT_STA_DT [계약기간시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약기간종료일]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : OP_TYP_CD [계약상태]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : FISCL_SVCL [내용년수]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : SLIP_NO [ERP전표번호]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 */
	public IDataSet pInqInveInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqInveInfoLst", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 * <pre>단말자산현황상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ASSET_NO [자산번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_REQ_IN_DTL
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : OP_TYP_CD [계약상태]
	 *		- field : FISCL_SVCL [내용년수]
	 *		- field : CAPA_CD [용량코드]
	 * </pre>
	 */
	public IDataSet pInqInveInfoDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqInveInfoDtl", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_INVE_HANDLE
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : EQP_PRCH_AMT [매입가 = 출고가]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : OP_TYP_CD [업무유형코드]
	 *		- field : INVE_ST_CD [계약상태코드]
	 *		- field : CHK [콤보박스값 1로 받음]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : SLIP_NO [ERP용 전표번호]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSlipInveInfoHandl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    	 		
	    try {
	    	// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fSlipInveInfoHandl", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약일 FROM]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_REQ_IN
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_MDL_NM [단말기 모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : RENTAL_CNTRT_STA_DT [계약기간시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약기간종료일]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : OP_TYP_CD [계약상태]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : FISCL_SVCL [내용년수]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : SLIP_NO [ERP전표번호]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 */
	public IDataSet pSlipInveInfoAll(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	   
	  
	    try {
			// 1. FM 호출
	    	IDataSet dsAll = callSharedBizComponentByDirect("nr.NRSEABase", "fInqInveInfoAll", requestData, onlineCtx); //받은게 RS_REQ_IN
	    	IRecordSet rs = dsAll.getRecordSet("RS_REQ_IN");
	    	rs.setId("RS_SLIP_INVE_HANDLE");
	    	
	    	responseData.putRecordSet(rs);
	    	
	    	callSharedBizComponentByDirect("nr.NRSEABase", "fSlipInveInfoHandl", responseData, onlineCtx); //받은게 ERP_SLIP_LIST
	    	   	
	    	} catch ( BizRuntimeException e ) {
				throw e;
			} catch ( Exception e ) {
				throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
			}
			// 3. 결과값 리턴
			responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
			
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_DELETE
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_MDL_NM [단말기 모델명]
	 *		- field : PET_NM [펫네임]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : NEW_CNTRT_DT [계약일]
	 *		- field : RENTAL_CNTRT_STA_DT [계약기간시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약기간종료일]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : FISCL_REMPRC [잔존가]
	 *		- field : OP_TYP_CD [계약상태]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : COLOR_CD [색상코드]
	 *		- field : FISCL_SVCL [내용년수]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : SLIP_NO [ERP용 전표번호]
	 *		- field : YYYY [전표처리년도]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSlipInveInfoHandlDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    boolean isOnline = false;
	    Log log = getLog(onlineCtx);
	    CommonArea ca = getCommonArea(onlineCtx);
	   	    
	    IRecordSet rs = requestData.getRecordSet("RS_SLIP_DELETE");
	
	    try {
	    	//전표요청 중이 아니면 exception처리
	    	for(int i=0; i<rs.getRecordCount();i++){
	    		if (!"10".equals(rs.get(i, "ASSET_SLIP_ST"))){
	    			    			
	    			throw new BizRuntimeException("XYZE0000", new String[] {"전표취소처리 "}); //{0} 중 오류가 발생하였습니다.
	    		}
	    	}
	    	
	    	// ERP용 전표번호 추가
	    	IRecord rd = null;
	    	for(int i=0; i<rs.getRecordCount(); ++i){
				rd = rs.getRecord(i);
				rd.set("SLIP_NO", rd.get("ASSET_SLIP_NO"));
				}
	    	if(isOnline)
	    	{
		    	// 1. FM 호출
				responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fSlipInveInfoHandlDel", requestData, onlineCtx);
	    	}
	    	else
	    	{
	    		
				ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
				DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
				String dsXml = bout.toString("UTF-8");

				// call on-demand batch job
				HashMap params = new HashMap<String,String>();
				params.put("TASK_ID", "EPR011");
			    params.put("TASK_NM", "전표삭제");
			    params.put("USER_NO", ca.getUserNo());
			    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");				
				params.put("POST_SLIP_DATASET", dsXml);
				String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
			    waitBatchJobEnd(jobExecutionId, 10000);
			    int result = getJobReturnCode(jobExecutionId);
			    
			    
				log.debug("(((((((((((((((((((((((((((((((pSlipInveInfoHandlDel() result :"+ result);

			    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.	    		
	    	}
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	    	
	
	    return responseData;
	    
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *	- field : INVE_ST_CD [필드6]
	 *	- field : OP_TYP_CD [필드7]
	 *	- field : ASSET_SLIP_ST [필드8]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_CONFIRM
	 *		- field : TOT_CNT [총건수]
	 *		- field : TOT_PRC [총금액]
	 * </pre>
	 */
	public IDataSet pSlipInveInfoConfirm(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fSlipInveInfoConfirm", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
		// 3. 결과값 리턴
		responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-07-17 15:48:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [단말기 코드]
	 *	- field : RENTAL_CNTRT_STA_DT [계약기간시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약기간종료일]
	 *	- field : OP_TYP_CD [계약상태]
	 *	- field : INVE_ST_CD [단말상태]
	 *	- field : ASSET_SLIP_ST [전표상태]
	 *	- field : EQP_SER_NO [일련번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqInveAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
               // 1. FM 호출
               responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqInveAllExcel", requestData, onlineCtx);
           } catch ( BizRuntimeException e ) {
               throw e;
           } catch ( Exception e ) {
               throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
           }
           // 3. 결과값 리턴
           responseData.setOkResultMessage("DMS00001", null); // 정상 조회되었습니다.
    
        // 처리 결과값을 responseData에 넣어서 리턴하십시요 
    
        return responseData;
    }    	
}