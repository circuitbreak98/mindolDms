package dms.pr.prbimbase.biz;

import java.util.Map;

import fwk.common.CommonArea;
import fwk.constants.DmsConstants;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.StringUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>단위업무명: [PU]회수대상임대폰조회</li>
 * <li>설  명 : <pre>회수대상임대폰조회</pre></li>
 * <li>작성일 : 2015-07-14 20:45:43</li>
 * <li>작성자 : 이준우 (elmsliw)</li>
 * </ul>
 *
 * @author 이준우 (elmsliw)
 */
public class PPREqpTransportMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PPREqpTransportMgmt(){
		super();
	}

	/**
	 * <pre>[PM]단말기운송조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-03 16:08:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [시작일]
	 *	- field : ED_DT [종료일]
	 *	- field : ST_DT_SEND [필드1]
	 *	- field : ED_DT_SEND [필드2]
	 *	- field : EQP_MDL_CD [모델]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : EQP_SER_NO [시리얼넘버]
	 *	- field : WAYBIL_NO [입고번호]
	 *	- field : HDLV_SNDR_NM [발송자명]
	 *	- field : INVE_ST_CD [상태]
	 *	- field : EQP_WAYBIL_NO [단말기운송장번호(k)]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_WAYBIL_NO [단말기운송장번호]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLVCO_NM [택배사명]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : INVE_ST_CD [회수구분코드(자산)]
	 *		- field : LAST_IN_OUT_NO [최종출고번호(자산)]
	 *		- field : EQP_IN_NO [단말기입고번호(입고)]
	 *		- field : IN_DTL_CD [입고상세코드(입고)]
	 *		- field : OBJ_IN_DT [단말기입고일자(입고)]
	 * </pre>
	 */
	/**
	 * <pre>[PM]단말기운송조회</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-03 16:08:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ST_DT [시작일]
	 *	- field : ED_DT [종료일]
	 *	- field : ST_DT_SEND [필드1]
	 *	- field : ED_DT_SEND [필드2]
	 *	- field : EQP_MDL_CD [모델]
	 *	- field : EQP_MDL_NM [모델명]
	 *	- field : EQP_SER_NO [시리얼넘버]
	 *	- field : WAYBIL_NO [입고번호]
	 *	- field : HDLV_SNDR_NM [발송자명]
	 *	- field : INVE_ST_CD [상태]
	 *	- field : EQP_WAYBIL_NO [단말기운송장번호(k)]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_LIST
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [단말기모델명]
	 *		- field : EQP_WAYBIL_NO [단말기운송장번호]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLVCO_NM [택배사명]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : INVE_ST_CD [회수구분코드(자산)]
	 *		- field : LAST_IN_OUT_NO [최종출고번호(자산)]
	 *		- field : EQP_IN_NO [단말기입고번호(입고)]
	 *		- field : IN_DTL_CD [입고상세코드(입고)]
	 *		- field : EQP_IN_DT [단말기입고일자(입고)]
	 * </pre>
	 */
    public IDataSet pEqpTransportMgmt(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
    	try {

    		responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fInqEqpTransInfoLst", requestData, onlineCtx);
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}		
        return responseData;
    }

    /**
	 * <pre>[PM]단말기운송정보저장</pre>
	 *
	 * @author 이준우 (elmsliw)
	 * @since 2015-08-03 16:08:54
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_ETMM_TEMP_LIST
	 *		- field : EQP_MDL_CD [단말기모델코드]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : DEL_YN [삭제여부]
	 *		- field : EQP_WAYBIL_NO [단말기운송장번호]
	 *		- field : EQP_RCV_DT [단말기수취일자]
	 *		- field : WAYBIL_NO [운송장번호]
	 *		- field : HDLVCO_CD [택배사코드]
	 *		- field : HDLV_AMT [택배비]
	 *		- field : HDLV_SNDR_NM [택배발송자명]
	 *		- field : HDLV_SND_DT [택배발송일자]
	 *		- field : HDLV_SND_RSN [택배발송사유]
	 *		- field : LS_CHG_USER_ID [최종수정자ID]
	 *		- field : LS_CHG_DTM [최종수정일시]
	 *		- field : EQP_IN_DT [단말기입고일자(입고)-수취일자]
	 *		- field : EQP_IN_NO [단말기입고번호(입고)]
	 *		- field : IN_DTL_CD [입고상세코드(입고)]
	 *		- field : LAST_IN_OUT_NO [최종출고번호(자산)]
	 *		- field : INVE_ST_CD [입고상세코드(자산)]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
    public IDataSet pSaveEqpTransInfo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    //    IDataSet responseSeqData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
        
    
        
    	try {
    		// 1. 입력 RS설정
    	    IRecordSet rs = requestData.getRecordSet("RS_ETMM_TEMP_LIST");	//운송리스트정보
    		
    		// 2. 레코드셋 상태에 따른 분기
    		for (int i=0; i<rs.getRecordCount(); i++) {
    			requestData.putFieldMap(rs.getRecordMap(i));
    			
    			// 3. 최종변경사용자ID
    			requestData.putField("FS_REG_USER_ID", ca.getUserNo());
    			requestData.putField("LS_CHG_USER_ID", ca.getUserNo());
    
    			IRecord record = rs.getRecord(i); //운송리스트정보
    			
    			if ( StringUtils.equals(DmsConstants.STATUS_INSERTED, record.get(DmsConstants.RECORD_STATUS)) ){
    				responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fRegEqpTransInfo", requestData, onlineCtx);
    			} else if ( StringUtils.equals(DmsConstants.STATUS_UPDATED, record.get(DmsConstants.RECORD_STATUS)) ){
    				responseData = callSharedBizComponentByDirect("pr.PRSIMBase", "fUpdEqpTransInfo", requestData, onlineCtx);
    			} 
    			
    	
    		}			
    	} catch ( BizRuntimeException e ) {
    		throw e;
    	} catch ( Exception e ) {
    		throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
    	}
    	// 4. 결과값 리턴
    	responseData.setOkResultMessage("DMS00000", null); //정상 처리되었습니다.
    	
        return responseData;
    }

}