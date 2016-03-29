package dms.nr.nrbeabase.biz;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]단말제각관리</li>
 * <li>설  명 : <pre>분실 단말기에대한 제각 처리정보를 조회한다.</pre></li>
 * <li>작성일 : 2015-08-06 08:52:40</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNREqpExcMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNREqpExcMgmt(){
		super();
	}

	/**
	 * <pre>[PM]단말제각현황조회</pre>
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DEPR_DT [제각일]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태코드]
	 *	- field : LST_DEPR_DT [LST_제각일]
	 *	- field : LST_INVE_ST_CD [LST_단말상태]
	 *	- field : LST_ASSET_SLIP_NO [LST_전표]
	 *	- field : LST_ASSET_SLIP_ST [LST_전표처리상태]
	 *	- field : LST_DISPO_DT [LST_마감자산처분일자]
	 *	- field : ASSET_DISPO_STRD_YM [기준년월]
	 *	- field : DISPO_TS [처분차수]
	 *	- field : OP_CL_CD [업무구분코드]
	 *	- field : DSPSL_DIS_CL [매각제각구분]
	 *	- field : DISPO_DT [처분일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_EXC_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : REM_PRC [회계용잔존가]
	 *		- field : DEPR_PRC [회계용감가상각]
	 *		- field : EQP_LOSS_DT [분실일자]
	 *		- field : EQP_PRCH_DT [입고일=계약일]
	 *		- field : CAPA_CD [용량]
	 *		- field : PROV_PRC [충당부채]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : INVE_ST_CD [단말상태코드]
	 *		- field : ASSET_NO [자산번호]
	 * </pre>
	 */
	public IDataSet pInqEqpExcLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    try {
	        Log log = getLog(onlineCtx);
	        log.debug("pInqEqpExcLst() requestData :"+ requestData); 
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpExcLst", requestData, onlineCtx);
			
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
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_SER_NO [일련번호]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : DEPR_DT [제각일자]
	 *		- field : EQP_PRCH_AMT [매입금액]
	 *		- field : REM_PRC [잔존가]
	 *		- field : DEPR_PRC [감가상각-제각]
	 *		- field : EQP_LOSS_DT [분실일자]
	 *		- field : EQP_PRCH_DT [매입일]
	 *		- field : PROV_PRC [충당부채-제각]
	 *		- field : ASSET_SLIP_ST [제각전표번호]
	 *		- field : ASSET_SLIP_NO [제각전표상태]
	 *		- field : INVE_ST_CD [단말상태]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : PRE_DEPR_SUM [감가상각-누락]
	 *		- field : PRE_PROV_SUM [충당부채-누락]
	 *		- field : DEPR_SLIP_NO [감가상각전표]
	 *		- field : DEPR_SLIP_ST_CD [감가상각전표상태]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태코드]
	 *	- field : DEPR_DT [제각일]
	 *	- field : CASE_WHEN [분기용]
	 *	- field : LST_DEPR_DT [분기용제각일]
	 *	- field : LST_INVE_ST_CD [분기용단말상태]
	 *	- field : LST_ASSET_SLIP_NO [분기용전표번호]
	 *	- field : LST_ASSET_SLIP_ST [분기용전표상태]
	 *	- field : LST_DISPO_DT [마감자산처분일자]
	 *	- field : YYYY [분기용전표년도]
	 *	- field : ASSET_DISPO_STRD_YM [기준일자]
	 *	- field : DISPO_TS [처분차수]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pEqpExcBatchCallOnline(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
            //1. FM 호출
	        //전표취소시 _마감자산처분 정보를 삭제한다
//            if("03".equals(requestData.getField("CASE_WHEN"))||StringUtils.isNotEmpty(requestData.getField("LST_DISPO_DT"))){
//                responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fDelExcClsAssetDispo", requestData, onlineCtx);
//            }
            //  
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpExcBatchCallOnline", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_POWER_EXC_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : REM_PRC [회계용잔존가]
	 *		- field : DEPR_PRC [회계용감가상각]
	 *		- field : EQP_LOSS_DT [분실일자]
	 *		- field : EQP_PRCH_DT [입고일=계약일]
	 *		- field : CAPA_CD [용량]
	 *		- field : PROV_PRC [충당부채]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : INVE_ST_CD [단말상태코드]
	 *		- field : ASSET_NO [자산번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pPowerExcController(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fPowerExcController", requestData, onlineCtx);
		} catch ( BizRuntimeException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
		}
	
	    return responseData;
	}

    /**
	 * <pre>[PM]단말제각현황전체다운로드</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태코드]
	 *	- field : DEPR_DT [제각일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqEqpExcAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
               // 1. FM 호출
               responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpExcAllExcel", requestData, onlineCtx);
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
	 * <pre>[PM]단말자산전표상태재조회</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-10-26 15:57:42
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_DT [계약일검색시작]
	 *	- field : END_DT [계약일검색종료]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pDisSlipReStateCall(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
               // 1. FM 호출
               responseData = callSharedBizComponentByDirect("nr.NRSXMBase", "fSlipInfoSynchronization", requestData, onlineCtx);
           } catch ( BizRuntimeException e ) {
               throw e;
           } catch ( Exception e ) {
               throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
           }
           
        return responseData;
    }

    /**
	 * <pre>[PM]단말제각전표처리현황</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DEPR_DT [제각일]
	 *	- field : ASSET_SLIP_NO [전표번호]
	 *	- field : ASSET_SLIP_ST [전표상태]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_SUM_LIST
	 *		- field : TOTAL_CNT [총건수]
	 *		- field : DEPR_PRC [감가상각누계액합계]
	 *		- field : PROV_PRC [충당부채누계액합계]
	 *		- field : EQP_PRCH_AMT [매입금액]
	 *		- field : REM_PRC [잔존금액]
	 *		- field : LDTA_AMT [유형자산처분손실금_정산용]
	 *		- field : DEPR_DT [제각일자]
	 *		- field : INVE_ST_CD [단말상태코드]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : ASSET_SLIP_ST [전표처리상태]
	 *		- field : DISPO_DT [마감자산처분일자]
	 *		- field : YYYY [전표년도]
	 * </pre>
	 */
	public IDataSet pInqEqpExcSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        try {
            // 1. FM 호출
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpExcSum", requestData, onlineCtx);
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
	 * <pre>[PM]전표제각마감자산처분</pre>
	 *
	 * @author 문재웅 (jaiwoong)
	 * @since 2015-08-06 08:52:40
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_EXC_CLS_ASSET_DISPO_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : DEPR_DT [검색기준일]
	 *		- field : REM_PRC [회계용잔존가]
	 *		- field : DEPR_PRC [회계용감가상각]
	 *		- field : SALE_PRC [예상매각가]
	 *		- field : EQP_PRCH_DT [계약일 = 입고일]
	 *		- field : PROV_PRC [충당부채기타누계액]
	 *		- field : CAPA_CD [용량]
	 *		- field : ASSET_SLIP_ST [전표상태]
	 *		- field : ASSET_SLIP_NO [전표번호]
	 *		- field : INVE_ST_CD [단말상태코드]
	 *		- field : ASSET_NO [자산번호]
	 *	- field : DEPR_DT [제각일]
	 *	- field : LST_DEPR_DT [분기용제각일]
	 *	- field : LST_DISPO_DT [마감자산처분일자]
	 *	- field : LST_ASSET_SLIP_NO [분기용전표번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pSlipExcClsAssetDispo(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        CommonArea ca = getCommonArea(onlineCtx);
        
        try {
            // 1. FM 호출
            requestData.putField("USER_NO", ca.getUserNo());
            
            //if(StringUtils.isNotEmpty(requestData.getField("LST_DEPR_DT"))&&StringUtils.isEmpty(requestData.getField("LST_ASSET_SLIP_NO"))){
            if(StringUtils.isNotEmpty(requestData.getField("LST_DISPO_DT"))){    
                responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fDelExcClsAssetDispo", requestData, onlineCtx);
            }
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fRegExcClsAssetDispoDtl", requestData, onlineCtx);
            responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fRegExcClsAssetDispo", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
        
        return responseData;
    }
  
}