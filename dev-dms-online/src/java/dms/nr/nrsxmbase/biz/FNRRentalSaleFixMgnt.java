package dms.nr.nrsxmbase.biz;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.xml.DataSetXmlTransformer;
import nexcore.framework.core.exception.BizRuntimeException;

import org.apache.commons.logging.Log;

import fwk.common.CommonArea;
import fwk.utils.HpcUtils;
import fwk.utils.PagenateUtils;


/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [FU]렌탈매출확정정보관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-08-07 13:10:17</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class FNRRentalSaleFixMgnt extends fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FNRRentalSaleFixMgnt(){
		super();
	}

	/**
	 *렌탈매출확정조회
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-07 13:10:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqRentalSaleFixLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	      
	      IDataSet dsCnt = new DataSet();
		  IDataSet reqDs = (IDataSet) requestData.clone();//원거래의 DataSet의 훼손을 막기 위한 Clone
		  IRecordSet usrListRs = null;
		  int intTotalCnt = 0;
		  
		  try {
				// 1. DU lookup
			  DNRRentalSaleFixMgnt   dNRRentalSaleFixMgnt = (DNRRentalSaleFixMgnt) lookupDataUnit(DNRRentalSaleFixMgnt.class);
				
				// 2. TOTAL COUNT DM호출
				dsCnt = dNRRentalSaleFixMgnt.dSRentalSaleTotCnt(requestData, onlineCtx);
				intTotalCnt = Integer.parseInt(dsCnt.getField("TOTAL_CNT"));
				PagenateUtils.setPagenatedParamsToDataSet(reqDs);

				// 3. LISTDM호출
				responseData = dNRRentalSaleFixMgnt.dSRentalSaleLstPaging(reqDs, onlineCtx);
				usrListRs = responseData.getRecordSet("RS_RENTAL_SALE_FIX_LIST");
				PagenateUtils.setPagenatedParamToRecordSet(usrListRs, reqDs, intTotalCnt);
				
				//렌탈매출확정 기준일자 리스트조회
				IDataSet dsRtn = dNRRentalSaleFixMgnt.dSrentalSaleAsmptLst(requestData, onlineCtx);
				responseData.putRecordSet(dsRtn.getRecordSet("RS_SALE_SUM"));
				
				//매출추정정보 개인,법인사용자 리스트조회
				IDataSet dsRtn2 = dNRRentalSaleFixMgnt.dSRentalSaletPriLst(requestData, onlineCtx);
				responseData.putRecordSet(dsRtn2.getRecordSet("RS_SALE_ASMPT_PRI_LIST"));
				
			} catch ( BizRuntimeException e ) {
				throw e; //시스템 오류가 발생하였습니다.
			}
	
	    return responseData;
	}

	/**
	 *렌탈매출확정상세
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-07 14:39:27
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqRentalSaleFixDtlLst(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    try {
			// 1. DU lookup
	    	DNRRentalSaleFixMgnt dNRRentalSaleFixMgnt = (DNRRentalSaleFixMgnt) lookupDataUnit(DNRRentalSaleFixMgnt.class);
	    	requestData.putField("SVC_NO", HpcUtils.encodeByAES(requestData.getField("SVC_NO")));

			// 2. LISTDM호출
			responseData = dNRRentalSaleFixMgnt.dSRentalSaleDtlLst(requestData, onlineCtx);
			
			//렌탈매출확정상세 기준일자 리스트조회
			IDataSet dsRtn = dNRRentalSaleFixMgnt.dSrentalSaleAsmptDtlLst(requestData, onlineCtx);
			responseData.putRecordSet(dsRtn.getRecordSet("RS_SALE_SUM"));
			
		} catch ( BizRuntimeException e ) {
			throw e; //시스템 오류가 발생하였습니다.
		} 
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-07 13:10:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : YDATE [필드1]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqRentalReBatch(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    CommonArea ca = getCommonArea(onlineCtx);
	
	    try {	
	    	
		    ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
			DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
			String dsXml = bout.toString("UTF-8");
		
			// call on-demand batch job
			HashMap params = new HashMap<String,String>();
			params.put("TASK_ID", "XCR011");
		    params.put("TASK_NM", "렌탈매출확정");
		    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
		    params.put("USER_NO", ca.getUserNo());
		    params.put("PROC_DT", requestData.getField("YDATE")+"01");
		    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.nr.XCR011");				
			params.put("ONDEMAND_DATASET", dsXml);
			
			log.debug("(((((((((((((((((((((((((((((((fInqSaleReBatch() params :"+ params);
			
			String jobExecutionId = callBatchJob("XCR011", params, onlineCtx);
		    waitBatchJobEnd(jobExecutionId, 10000);
		    int result = getJobReturnCode(jobExecutionId);
		    
		    
			log.debug("(((((((((((((((((((((((((((((((fInqSaleReBatch() result :"+ result);
		
		    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다.
		    
	    } catch ( BizRuntimeException e ) {
	    	throw e; //시스템 오류가 발생하였습니다.
	
	    } catch (UnsupportedEncodingException ee) {
	    	throw new BizRuntimeException("DMS00009",ee); //시스템 오류가 발생하였습니다.
	    }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-07 13:10:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_RENTAL_SALE_FIX_SLIP_LIST
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : CUST_TYP [고객유형]
	 *		- field : MTH_RENTAL_FEE [잔액]
	 *		- field : GUBUN [구분]
	 *		- field : SELEC [필드6]
	 *		- field : ASMPT_SLIP_NO [전표번호]
	 *		- field : ASMPT_SLIP_ST [전표상태]
	 *		- field : SALE_ASMPT_YM [년월]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveRentalSlip(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    CommonArea ca = getCommonArea(onlineCtx);
        Log log = getLog(onlineCtx);
        IRecordSet rs = requestData.getRecordSet("RS_RENTAL_SALE_FIX_SLIP_LIST");
        boolean isOnline = false;

        try {
        	
        	DNRRentalSaleFixMgnt dNRRentalSaleFixMgnt = (DNRRentalSaleFixMgnt) lookupDataUnit(DNRRentalSaleFixMgnt.class);
        	
        	//for (int i=0; i< rs.getRecordCount(); i++){
        		
        	
            // 1. 입력 RS설정
            requestData.putField("USERNO", ca.getUserNo());
            requestData.putField("SLIP_TYPE", "RENTAL_SALE");
            
    			log.debug("((((((((((((((((((((((((((((((((fSaveRentalSlip() fSaveRentalSlip requestData:"+ requestData);
    			 
    			
    		    ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
    			DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
    			String dsXml = bout.toString("UTF-8");
    		//
    			// call on-demand batch job
    			HashMap params = new HashMap<String,String>();
    			params.put("TASK_ID", "EPR010");
    		    params.put("TASK_NM", "전표발행");
    		    params.put("LGIN_ID", onlineCtx.getUserInfo().getLoginId());
    		    params.put("USER_NO", ca.getUserNo());
    		    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR010");				
    			params.put("POST_SLIP_DATASET", dsXml);
    			
    			log.debug("(((((((((((((((((((((((((((((((fSaveRentalSlip() params :"+ params);
    			
    			String jobExecutionId = callBatchJob("EPR010", params, onlineCtx);
    		    waitBatchJobEnd(jobExecutionId, 10000);
    		    int result = getJobReturnCode(jobExecutionId);
    		    
    		    
    			log.debug("(((((((((((((((((((((((((((((((fSaveRentalSlip() result :"+ result);
    		
    		    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발

        	//}
        } catch ( BizRuntimeException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new BizRuntimeException("DMS00009", e); //시스템 오류
        }
	
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-08-07 13:10:17
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- record : RS_SLIP_DELETE
	 *		- field : DEALCO_BLICENS_NO [사업자번호]
	 *		- field : NEW_CNTRTR_NM [고객명]
	 *		- field : CUST_TYP [고객유형]
	 *		- field : MTH_RENTAL_FEE [렌탈료]
	 *		- field : GUBUN [구분]
	 *		- field : SELEC [필드6]
	 *		- field : SLIP_NO [전표번호]
	 *		- field : SLIP_ST_CD [전표상태]
	 *		- field : SALE_ASMPT_YM [매출년월]
	 *		- field : YYYY [년]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fSaveRentalSaleSlipDel(IDataSet requestData, IOnlineContext onlineCtx) {
			Log log = getLog(onlineCtx);
		    IDataSet responseData = new DataSet();
		    CommonArea ca = getCommonArea(onlineCtx);
		    IRecordSet rs = requestData.getRecordSet("RS_SLIP_DELETE");
		    boolean isOnline = false;
		    
		    try{
		    	
		    	//for(int i=0; i< rs.getRecordCount(); i++){

					ByteArrayOutputStream bout = new ByteArrayOutputStream(1024);
					DataSetXmlTransformer.dataSetToXml(requestData, bout, "UTF-8");
					String dsXml = bout.toString("UTF-8");

					// call on-demand batch job
					HashMap params = new HashMap<String,String>();
					params.put("TASK_ID", "EPR011");
				    params.put("TASK_NM", "전표삭제");
				    params.put("USER_NO", ca.getUserNo());
				    params.put("COMPONENTNAME_LOCAL_ONLY", "dms.inf.EPR011");				
					params.put("POST_SLIP_DATASET", dsXml);
					log.info("(((((((((((((((((((((((((((((((fSaveRentalSaleSlipDel() params :"+ params);
					String jobExecutionId = callBatchJob("EPR011", params, onlineCtx);
				    waitBatchJobEnd(jobExecutionId, 10000);
				    int result = getJobReturnCode(jobExecutionId);
				    
				    
					log.info("(((((((((((((((((((((((((((((((fSaveRentalSaleSlipDel() result :"+ result);

				    if(result == -1) throw new BizRuntimeException("DMS00009"); // 시스템 오류가 발생하였습니다
		    	
		    	//}
		    } catch(BizRuntimeException e){
		    	throw e;
		    } catch ( Exception e ) {
				throw new BizRuntimeException("DMS00009", e); // 시스템 오류가 발생하였습니다.
			}
		    
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-02 13:20:53
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet fInqRentalSaleFixAllExcel(IDataSet requestData, IOnlineContext onlineCtx) {
	
	    IDataSet responseData = new DataSet();
        IDataSet temp = new DataSet();
        
        try {
      
           // 1. DU lookup
        	DNRRentalSaleFixMgnt dNRRentalSaleFixMgnt = (DNRRentalSaleFixMgnt) lookupDataUnit(DNRRentalSaleFixMgnt.class);

            // 2. LISTDM호출
            responseData = dNRRentalSaleFixMgnt.dSRentalSaleFixAllExcel(requestData, onlineCtx); 
                
        } catch ( BizRuntimeException e ) {
            throw e; //시스템 오류가 발생하였습니다.
        }
    
        return responseData;
    }
	
}