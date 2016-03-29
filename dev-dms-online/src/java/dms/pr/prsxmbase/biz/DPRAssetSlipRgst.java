package dms.pr.prsxmbase.biz;

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
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [DU]재고정산전표등록</li>
 * <li>설  명 : <pre>[DU]재고정산전표등록</pre></li>
 * <li>작성일 : 2015-10-06 15:12:23</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class DPRAssetSlipRgst extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DPRAssetSlipRgst(){
		super();
	}

	/**
	 * <pre>[DM]재고정산전표대상조회</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-06 15:12:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqAssetSlipList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {       
	    	// 1.쿼리문 호출
	    	IRecordSet set  = dbSelect("SInqAssetSlipList", requestData, onlineCtx);
	    	// 2.결과값 셋팅
            responseData.putRecordSet("RS_SALE_XCL_LIST", set);
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 	
	    return responseData;
	}

	/**
	 * <pre>[DM]재고정산전표등록엑셀리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-06 15:12:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSInqAssetSlipExcelList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {       
	    	// 1.쿼리문 호출
	    	IRecordSet set = null;
	    	
	    	if( "AC".equals(requestData.getField("XCL_GUBUN") ) ){
		    	//자산감가상각
	    		set  = dbSelect("SAssetDeprSlipExcelList", requestData, onlineCtx);
		    }else if( "AS".equals(requestData.getField("XCL_GUBUN") ) ){
		    	//자산매각
		    	set  = dbSelect("SAssetAsSlipExcelList", requestData, onlineCtx);
		    }else if( "AD".equals(requestData.getField("XCL_GUBUN") ) ){
		    	//자산제각
		    	set  = dbSelect("SAssetAdSlipExcelList", requestData, onlineCtx);
		    }else if( "AA".equals(requestData.getField("XCL_GUBUN") ) ){
		    	//자산제각
		    	set  = dbSelect("SAsssetAaSlipExcelList", requestData, onlineCtx);
		    }else if( "AP".equals(requestData.getField("XCL_GUBUN") ) ){
		    	//자산제각
		    	set  = dbSelect("SAsssetApSlipExcelList", requestData, onlineCtx);
		    }
	    	
	    	
	    	
	    	
	    	
	    	// 2.결과값 셋팅
            responseData.putRecordSet("RS_SALE_XCL_LIST", set);
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 	
	    return responseData;
	}

	/**
	 * <pre>[DM]자산매입전표발행리스트</pre>
	 *
	 * @author 김상오 (myneti99)
	 * @since 2015-10-06 15:12:23
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSAssetPrchSlipList(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {       
	    	// 1.쿼리문 호출
	    	IRecordSet set  = dbSelect("SAssetPrchSlipNoList", requestData, onlineCtx);
	    	// 2.결과값 셋팅
            responseData.putRecordSet("RS_SALE_XCL_LIST", set);
        }
        catch ( BizRuntimeException e ) {
            throw e;
        } 	
	    return responseData;
	}
  
}