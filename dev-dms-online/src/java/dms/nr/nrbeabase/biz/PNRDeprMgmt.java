package dms.nr.nrbeabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]고정자산감가상각조회</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-03 09:39:45</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNRDeprMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNRDeprMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-08-03 09:39:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [모델코드]
	 *	- field : DEPR_STRD_YM [기준년월]
	 *	- field : DEPR_TS [감가상각차수]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_DEPR_LST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말기일련번호]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_COLOR_CD [색상코드]
	 *		- field : PET_NM [펫네임]
	 *		- field : DEPR_OBJ_AMT [매입가]
	 *		- field : DEPR_DTL_REM_AMT [잔존가]
	 *		- field : DEPR_FISCL_ACNTB_AMT [감가상각회계장부가액]
	 *		- field : DEPR_DTL_SUM_AMT [감가상각누계액]
	 *		- field : DEPR_DTL_AMT [감가상각액]
	 *		- field : CAPA_CD [용량코드]
	 *		- field : EQP_PRCH_DT [매입일]
	 *		- field : DEPR_DTL_TS [회차]
	 *		- field : ALLWN_AMT [충당부채]
	 *		- field : ALLWN_SUM_AMT [충당부채누계]
	 *		- field : EQP_CMP_AMT_TOT [단말기배상액=손해배상액]
	 *		- field : EQP_JDG_DT [손배처리일시]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 *		- field : YYYY [감가상각년]
	 *		- field : DEPR_TS [감가상각차수]
	 *	- record : RS_SUM_LIST
	 *		- field : TOTAL_CNT [총건수]
	 *		- field : DEPR_AMT [감가상각총액]
	 *		- field : ALLWN_AMT [충당부채총액]
	 * </pre>
	 */
	public IDataSet pInqDeprLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqDeprLst", requestData, onlineCtx);
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
	 * @since 2015-08-03 09:39:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [감가상각월]
	 *	- field : DEPR_TS [감가상각차수]
	 *	- field : IS_RECALL [재집계/전표생성 구분값]
	 *	- record : RS_SLIP_LIST
	 *		- field : DEPR_STRD_YM [필드1]
	 *		- field : DEPR_AMT [필드2]
	 *		- field : DEPR_SUM_AMT [필드3]
	 *		- field : DEPR_CROVR_SUM_AMT [필드4]
	 *		- field : DISPO_PRE_PRFITS_AMT [필드5]
	 *		- field : DISPO_PRE_PRFITS_SUM_AMT [필드6]
	 *		- field : DEPR_TS [필드7]
	 *		- field : CNT [필드8]
	 *		- field : SLIP_NO [필드9]
	 *		- field : SLIP_DT [필드10]
	 *		- field : SLIP_ST_CD [필드11]
	 *		- field : YYYY [필드12]
	 *		- field : YYYYMM [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pDeprOnlineRecall(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fDeprOnlineRecall", requestData, onlineCtx);
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
	 * @since 2015-08-03 09:39:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [감가상각월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqDeprAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqDeprAllExcel", requestData, onlineCtx);
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
	 * @since 2015-08-03 09:39:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YYYYMM [PROC_DT용]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pDeprReStateCall(IDataSet requestData, IOnlineContext onlineCtx) {
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
	 *
	 *
	 * @author 안진갑 (bella21cjk)
	 * @since 2015-08-03 09:39:45
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DEPR_STRD_YM [감가상각기준년월]
	 *	- field : DEPR_TS [감가상각차수]
	 *	- field : SVC_MGMT_NO [서비스관리번호]
	 *	- field : EQP_MDL_CD [단말기모델코드]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pInqDeprDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
		
	    try {
	           // 1. FM 호출
	           responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqDeprDtlLst", requestData, onlineCtx);
	       } catch ( BizRuntimeException e ) {
	           throw e;
	       } catch ( Exception e ) {
	           throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
	       }
	    return responseData;
	}
  
}
