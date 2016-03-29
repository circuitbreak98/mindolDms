package dms.ep.epbfibase.biz;

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
 * <li>단위업무명: [PU]FPA가용재고관리</li>
 * <li>설  명 : <pre>FPA가용재고관리</pre></li>
 * <li>작성일 : 2016-02-17 11:33:01</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class PEPFPAAvailInveMgmt extends fwk.base.ProcessUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public PEPFPAAvailInveMgmt(){
		super();
	}

    /**
	 * <pre>FPA가용재고조회</pre>
	 *
	 * @author 양재석 (jsyang)
	 * @since 2016-02-17 11:33:01
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : CNSL_MGMT_NO [상담 관리 번호]
	 *	- field : EQP_MDL_CD [단말기 모델 코드]
	 *	- field : EQP_MDL_NM [단말기 모델 명]
	 *	- field : SELL_GRADE [단말기 판매 등급]
	 *	- field : MID_TERM_YN [중도 해지 여부]
	 *	- field : PRCH_CONF_YN [매입 확정 여부]
	 *	- field : BOX_NO [박스 번호]
	 *	- field : SELL_PROXY_YN [판매 대행 여부]
	 *	- field : SELL_YN [판매 여부]
	 *	- field : MFACT_CD [제조사 코드]
	 *	- field : EQP_SER_NO [단말기 일련 번호]
	 *	- field : PROD_SER_NO [상품 일련 번호]
	 *	- field : SALEFROMDTM [판매시작일자]
	 *	- field : SALETODTM [판매종료일자]
	 *	- field : MQ_SELL_YN [MQ판매여부]
	 *	- field : PROGR_ST [진행상태]
	 *	- field : SELL_TARGET [판매대상]
	 *	- field : TLYFROMDT [검수시작일자]
	 *	- field : TLYTODT [검수종료일자]
	 *	- field : PRCHFROMDT [매입시작일자]
	 *	- field : PRCHTODT [매입종료일자]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- record : RS_FPA_AVAIL_INVE_LIST
	 *		- field : CNSL_MGMT_NO [상담 관리 번호]
	 *		- field : EQP_MDL_CD [단말기 모델 코드]
	 *		- field : EQP_SER_NO [단말기 일련 번호]
	 *		- field : EQP_COLOR_CD [단말기 색상 코드]
	 *		- field : IMEI [IMEI]
	 *		- field : PRCH_AMT [매입 금액]
	 *		- field : SELL_GRADE [판매 등급]
	 *		- field : SELL_PRC [판매 가격]
	 *		- field : IN_CONF_DT [입고 확정 일자]
	 *		- field : IN_PROC_CHRGR_ID [입고 처리 담당자 ID]
	 *		- field : IN_PROC_CHRGR_NM [입고 처리 담당자 명]
	 *		- field : INPT [검수자]
	 *		- field : INPT_NM [검수자 명]
	 *		- field : TLY_DT [검수 일자]
	 *		- field : MID_TERM_YN [중도 해지 여부]
	 *		- field : PRCH_CONF_YN [매입 확정 여부]
	 *		- field : PRCH_CONF_DT [매입 확정 일자]
	 *		- field : BOX_NO [박스 번호]
	 *		- field : BOX_PROC_CHRGR [박스 처리 담당자]
	 *		- field : BOX_PROC_CHRGR_NM [박스 처리 담당자 명]
	 *		- field : BOX_PROC_DTM [박스 처리 일시]
	 *		- field : PROGR_ST [진행 상태]
	 *		- field : SELL_YN [판매 여부]
	 *		- field : SELL_PROXY_YN [판매 대행 여부]
	 *		- field : PROD_SER_NO [상품 일련 번호]
	 *		- field : MFACT_CD [제조사코드]
	 *		- field : SELL_PRC_VAT [판매가VAT포함]
	 *		- field : SELL_PRC_PROXY [대행판매금액]
	 * </pre>
	 */
	public IDataSet pInqFPAAvailInveList(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        try {
            //  FM 호출
            responseData = callSharedBizComponentByDirect("ep.EPSFIBase", "fInqFPAAvailInveList", requestData, onlineCtx);
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
  
}