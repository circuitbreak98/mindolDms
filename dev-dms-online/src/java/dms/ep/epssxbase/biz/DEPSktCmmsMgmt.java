package dms.ep.epssxbase.biz;

import java.util.Map;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>단위업무명: [DU]대리점수수료관리</li>
 * <li>설  명 : <pre>[DU]대리점수수료관리</pre></li>
 * <li>작성일 : 2015-10-05 10:36:23</li>
 * <li>작성자 : 김윤환 (kyh0904)</li>
 * </ul>
 *
 * @author 김윤환 (kyh0904)
 */
public class DEPSktCmmsMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DEPSktCmmsMgmt(){
		super();
	}

    /**
	 * <pre>[DM]SKT수수료상세목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSktCmmsDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SSktCmmsDtlLst", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_CMMS_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]선할인SKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIPreDcSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
	    // 1.쿼리문 호출
	    int row = dbInsert("IPreDcSktCmms", requestData, onlineCtx);
	    responseData.putField("insertRow", row);
	    return responseData;
    }

    /**
	 * <pre>[DM]계좌송금현장감정SKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIAccoRmtRpetJdgSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
	    // 1.쿼리문 호출
	    int row = dbInsert("IAccoRmtRpetJdgSktCmms", requestData, onlineCtx);	
	    responseData.putField("insertRow", row);
	    
        return responseData;
    }

    /**
	 * <pre>[DM]계좌송금비현장감정SKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIAccoRmtGnrlJdgSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
	    // 1.쿼리문 호출
	    int row = dbInsert("IAccoRmtGnrlJdgSktCmms", requestData, onlineCtx);
	    responseData.putField("insertRow", row);
	    
        return responseData;
    }

    /**
	 * <pre>[DM]요금선납비현장SKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFeePpayGnrlJdgSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
	    int row = dbInsert("IFeePpayGnrlJdgSktCmms", requestData, onlineCtx);
	    responseData.putField("insertRow", row);
	    
        return responseData;
    }

    /**
	 * <pre>[DM]요금선납현장SKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIFeePpayRpetJdgSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
        // 1.쿼리문 호출
	    int row = dbInsert("IFeePpayRpetJdgSktCmms", requestData, onlineCtx);
        responseData.putField("insertRow", row);
        
        return responseData;
    }

    /**
	 * <pre>[DM]선할인SKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSPreDcSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SPreDcSktCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]수수료정산삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-07 17:14:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDTbEpCmmsXcl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("DTbEpCmmsXcl", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]계좌송금비현장SKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAccoRmtGnrlJdgSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAccoRmtGnrlJdgCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]계좌송금현장SKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAccoRmtRpetJdgSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SAccoRmtRpetJdgSktCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]요금선납비현장SKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFeePpayGnrlJdgCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFeePpayGnrlJdgSktCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]요금선납현장SKT수수료집계목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSFeePpayRpetJdgSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SFeePpayRpetJdgSktCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]SKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-13 16:22:41
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출                
        IRecordSet rsReturn = dbSelect("SSktCmmsLst", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_CMMS_LIST", rsReturn);
        return responseData;
    }

    /**
	 * <pre>[DM]수수료정산번호조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-13 17:38:38
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSCmmsXclNoSeq(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출     
        IRecord record = dbSelectSingle("SCmmsXclNoSeq", requestData, onlineCtx);
        // 2.결과값 셋팅     
        responseData.putFieldMap(record); 
    
        return responseData;
    }

    /**
	 * <pre>[DM]수수료정산등록</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-13 17:38:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dITbEpCmmsXcl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbInsert("ITbEpCmmsXcl", requestData, onlineCtx);
        
        return responseData;
    }

    /**
	 * <pre>[DM]수수료정산상세삭제</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-14 14:02:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDTbEpCmmsXclDtl(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        dbUpdate("DTbEpCmmsXclDtl", requestData, onlineCtx);
        return responseData;
    }

    /**
	 * <pre>[DM]클럽TSKT수수료집계</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIClubTSktCmms(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        
        // 1.쿼리문 호출
        int row = dbInsert("IClubTSktCmms", requestData, onlineCtx);
        responseData.putField("insertRow", row);
        return responseData;
    }

    /**
	 * <pre>[DM]클럽TSKT수수료목록조회</pre>
	 *
	 * @author 김윤환 (kyh0904)
	 * @since 2015-10-05 10:36:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSClubTSktCmmsLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SClubTSktCmms", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_SKT_DTL_LIST", rsReturn);
        return responseData;
    }
  
}