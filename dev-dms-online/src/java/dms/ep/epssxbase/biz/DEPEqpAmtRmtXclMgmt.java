package dms.ep.epssxbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]단말기대금계좌송금정산관리</li>
 * <li>설  명 : <pre>단말기대금계좌송금정산관리 DU</pre></li>
 * <li>작성일 : 2015-10-23 09:57:17</li>
 * <li>작성자 : 이민재 (mindol1004)</li>
 * </ul>
 *
 * @author 이민재 (mindol1004)
 */
public class DEPEqpAmtRmtXclMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPEqpAmtRmtXclMgmt(){
		super();
	}

    /**
	 * <pre>단말기대금계좌송금조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 10:28:20
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpAmtRmtXclList", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SLIP_LIST", rsReturn);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금상세조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2015-10-23 10:28:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpAmtRmtXclDtl", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_AMTRMT_DTL", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-23 09:57:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclChk(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpAmtRmtXclChk", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-23 09:57:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclSeq(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SEqpAmtRmtXclSeq", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_EQP_ACCO_RMT_SEQ", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-23 09:57:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpAmtRmtXclDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        dbUpdate("UEqpAmtRmtXclDel", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-28 09:37:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dUEqpAmtRmtXclDtlDel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        dbUpdate("UEqpAmtRmtXclDtlDel", requestData, onlineCtx); 
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-28 09:38:30
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpAmtRmtXcl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
        dbInsert("IEqpAmtRmtXcl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 정동현 (jjddhh123)
	 * @since 2015-10-28 09:38:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpAmtRmtXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
	    dbInsert("IEqpAmtRmtXclDtl", requestData, onlineCtx);        
        
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금엑셀업로드검증조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-15 13:41:39
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpAmtRmtXclExcelList", requestData, onlineCtx);
        
        // 2.결과값 셋팅     
        if ( record != null ) {
            responseData.putFieldMap(record);
        }
    
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금엑셀저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-15 16:38:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpAmtRmtXclExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpAmtRmtXclExcel", requestData, onlineCtx);   
    
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금엑셀상세저장</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-15 16:39:26
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIEqpAmtRmtXclExcelDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("IEqpAmtRmtXclExcelDtl", requestData, onlineCtx);   
    
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금엑셀저장시퀀스조회</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-15 17:55:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclExcelSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpAmtRmtXclExcelSeq", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }

    /**
	 * <pre>단말기대금계좌송금엑셀검증체크</pre>
	 *
	 * @author 이민재 (mindol1004)
	 * @since 2016-01-19 16:04:12
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSEqpAmtRmtXclExcelChk(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SEqpAmtRmtXclExcelChk", requestData, onlineCtx);
        
        responseData.putFieldMap(record);
    
        return responseData;
    }
  
}