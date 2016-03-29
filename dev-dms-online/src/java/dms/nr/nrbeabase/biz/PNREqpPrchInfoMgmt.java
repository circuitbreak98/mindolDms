package dms.nr.nrbeabase.biz;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [PU]단말자산취득관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-09-22 16:59:50</li>
 * <li>작성자 : 장미진 (kuramotojin)</li>
 * </ul>
 *
 * @author 장미진 (kuramotojin)
 */
public class PNREqpPrchInfoMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PNREqpPrchInfoMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2015-09-22 16:59:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *	- field : SLIP_ST_CD [전표상태]
	 *	- field : SLIP_NO [전표번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_EQP_PRCH_LIST
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말일련번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : CNTRT_PRD [계약기간코드]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *		- field : EQP_PRCH_AMT [매입가]
	 *		- field : OP_TYP_CD [계약상태코드]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : YYYY [전표처리용년]
	 *	- record : RS_SLIP_LIST
	 *		- field : CNT [전표처리용총건수]
	 *		- field : PRC [전표처리총금액]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 *		- field : YYYY [전표처리년도]
	 *	- record : RS_SUM_LIST
	 *		- field : M_CNT [단말취득현황총건수]
	 *		- field : M_PRC [단말취득현황총금액]
	 *		- field : T_CNT [전표용총건수]
	 *		- field : T_PRC [전표용총금액]
	 * </pre>
	 */
	public IDataSet pInqEqpPrchInfoLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. FM 호출
			responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpPrchInfoLst", requestData, onlineCtx);
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
	 * @since 2015-09-22 16:59:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_LIST
	 *		- field : T_CNT [총건수]
	 *		- field : T_PRC [총금액]
	 *		- field : YYYY [전표처리년도]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : CHK [상태값]
	 *		- field : CASE_WHEN [구분값]
	 *		- field : YYYYMM [전표처리년월]
	 *		- field : STA_DT [계약일fr]
	 *		- field : END_DT [계약일to]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pEqpPrchInfoSlipHandle(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
	    	IRecordSet prchRs = requestData.getRecordSet("RS_SLIP_LIST");
	    	
	    	if("C".equals(prchRs.get(0,"CASE_WHEN"))){
	    		// 1.전표생성  FM 호출
	    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpPrchInfoSlipCreate", requestData, onlineCtx);
	    	}else if("D".equals(prchRs.get(0,"CASE_WHEN"))){
	    		// 2. 전표삭제 FM 호출
	    		responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fEqpPrchInfoSlipCancle", requestData, onlineCtx);
	    		
				}
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
	 * @since 2015-09-22 16:59:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : RENTAL_CNTRT_STA_DT [계약일조회시작일]
	 *	- field : RENTAL_CNTRT_END_DT [계약일조회종료일]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_ALL_EXCEL_LIST
	 *		- field : NO [NO]
	 *		- field : SVC_MGMT_NO [서비스관리번호]
	 *		- field : EQP_SER_NO [단말일련번호]
	 *		- field : ASSET_NO [자산번호]
	 *		- field : EQP_MDL_NM [모델명]
	 *		- field : EQP_MDL_CD [모델코드]
	 *		- field : CNTRT_PRD [계약기간]
	 *		- field : RENTAL_CNTRT_STA_DT [계약시작일]
	 *		- field : RENTAL_CNTRT_END_DT [계약종료일]
	 *		- field : EQP_PRCH_AMT [매입금액]
	 *		- field : OP_TYP_CD [계약상태코드]
	 *		- field : INVE_ST_CD [재고상태코드]
	 *		- field : SLIP_DT [전표처리일자]
	 *		- field : ACQR_XCL_SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태코드]
	 * </pre>
	 */
	public IDataSet pInqEqpPrchAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    try {
	           // 1. FM 호출
	           responseData = callSharedBizComponentByDirect("nr.NRSEABase", "fInqEqpPrchAllExcel", requestData, onlineCtx);
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
	 * @since 2015-09-22 16:59:50
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : STA_DT [계약일검색시작]
	 *	- field : END_DT [계약일검색종료]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet pReStateCall(IDataSet requestData, IOnlineContext onlineCtx) {
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
  
}