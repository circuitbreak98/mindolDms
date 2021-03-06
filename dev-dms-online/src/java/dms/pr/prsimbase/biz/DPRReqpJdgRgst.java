package dms.pr.prsimbase.biz;

import java.util.Map;

import org.apache.commons.logging.Log;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: 임대폰감정관리</li>
 * <li>설  명 : <pre>임대폰감정관리</pre></li>
 * <li>작성일 : 2015-07-21 16:46:05</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 *
 * @author 이영진 (newnofixing)
 */
public class DPRReqpJdgRgst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRReqpJdgRgst(){
		super();
	}

    /**
	 * <pre>임대폰감정등록조회</pre>
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-23 12:50:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dSReqpJdgRgstLst(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SReqpJdgRgstLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_REQP_JDG_LIST", rsReturn);
        
        // 1.쿼리문 호출
        IRecordSet rsReturn2 = dbSelect("SReqpJdgRgstCLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_REQP_JDG_CLIST", rsReturn2);
        
        IRecordSet rsReturn3 = dbSelect("DPRReqpJdgRgst.SReqJdgCmptLst", requestData, onlineCtx);
        responseData.putRecordSet("RS_JDG_CMPT_LIST", rsReturn3);
    
        return responseData;
    }

    /**
	 * <pre>[DM]임대폰감정저장</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-07-23 12:50:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dUReqpJdg(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        dbUpdate("UReqpJdg", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 * <pre>[DM]임대폰감정상세저장</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-07-23 12:50:29
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet dUReqpJdgDtl(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
//        System.out.println("USE_YN : "+requestData.getField("USE_YN"));
//        System.out.println(">>>>>>>>>>>>>>>>");
//        System.out.println("ASSET_NO:"+requestData.getField("ASSET_NO"));
//        System.out.println("EQP_JDG_SEQ:"+requestData.getField("EQP_JDG_SEQ"));
//        System.out.println("DCP_NO:"+requestData.getField("DCP_NO"));
//        System.out.println("INSP_DTL_ITM_CD:"+requestData.getField("INSP_DTL_ITM_CD"));
//        System.out.println(requestData.getFieldMap());
        // 1.쿼리문 호출
        dbUpdate("UReqpJdgDtl", requestData, onlineCtx);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 이영진 (newnofixing)
	 * @since 2015-07-30 16:55:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSReqpJdgRgstCLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	      
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SReqpJdgRgstCLst", requestData, onlineCtx);
        
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_REQP_JDG_CLIST", rsReturn);
    
        return responseData;
    }
  
}
