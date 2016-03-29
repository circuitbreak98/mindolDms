package dms.nr.nrsisbase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import fwk.common.CommonArea;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]보증보험기각정보조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-10-12 15:55:50</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNRInsuInvRjctLstMgnt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRInsuInvRjctLstMgnt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-12 15:56:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInsuInvRjctLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    IDataSet dsCnt = new DataSet();
	    //IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
	    IRecordSet usrListRs =  null;
	    int intTotalCnt = 0;
	    try {
		    // 1. DU lookup
		    DNRInsuInvRjctLstMgnt dNRInsuInvRjctLstMgnt = (DNRInsuInvRjctLstMgnt) lookupDataUnit(DNRInsuInvRjctLstMgnt.class);
			 	
			
		    // 2. TOTAL COUNT DM호출
			dsCnt = dNRInsuInvRjctLstMgnt.dSInsuInvRjctTotCnt(requestData, onlineCtx);
			IRecordSet sumRs = dsCnt.getRecordSet("RS_SUM_LIST");
			intTotalCnt = Integer.parseInt(sumRs.getRecord(0).get("TOTAL_CNT"));
			PagenateUtils.setPagenatedParamsToDataSet(requestData);
			responseData.putRecordSet(sumRs);
			
			// 3. LISTDM호출
			IDataSet listData = dNRInsuInvRjctLstMgnt.dSInsuInvRjctLstPaging(requestData, onlineCtx);
			usrListRs = listData.getRecordSet("RS_INSU_INV_RJCT_LIST");
			responseData.putRecordSet(usrListRs);
			PagenateUtils.setPagenatedParamToRecordSet(usrListRs, requestData, intTotalCnt);
		
		
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-15 13:46:28
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInsuReq(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    try {
            // 1. DU lookup
	        DNRInsuInvRjctLstMgnt dNRInsuInvRjctLstMgnt = (DNRInsuInvRjctLstMgnt) lookupDataUnit(DNRInsuInvRjctLstMgnt.class);
	        requestData.putField("USER_NO", ca.getUserNo());
	        
	        responseData = dNRInsuInvRjctLstMgnt.dUinsuReq(requestData, onlineCtx);
        
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
	    

	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-10-15 16:17:00
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveInsuDecideReq(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    try {
            // 1. DU lookup
	        DNRInsuInvRjctLstMgnt dNRInsuInvRjctLstMgnt = (DNRInsuInvRjctLstMgnt) lookupDataUnit(DNRInsuInvRjctLstMgnt.class);
	        requestData.putField("USER_NO", ca.getUserNo());
	        
	        responseData = dNRInsuInvRjctLstMgnt.dUinsuDecideReq(requestData, onlineCtx);
        
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
	
	    return responseData;
	}
  
}