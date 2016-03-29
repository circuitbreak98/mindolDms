package dms.nr.nrsisbase.biz;

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
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>단위업무명: [DU]미납채권조회및제각관리</li>
 * <li>설  명 : </li>
 * <li>작성일 : 2015-11-11 09:41:57</li>
 * <li>작성자 : 김혁범 (kezmain1)</li>
 * </ul>
 *
 * @author 김혁범 (kezmain1)
 */
public class DNRUnpdBondDisProcMgmt extends fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DNRUnpdBondDisProcMgmt(){
		super();
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-11 09:43:31
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUnpdBondDisProcTotCnt(IDataSet requestData, IOnlineContext onlineCtx) {
		 IDataSet responseData = new DataSet();
		 // 1.쿼리문 호출	    
  		IRecord record = dbSelectSingle("SUnpdBondDisProcTotCnt", requestData, onlineCtx);
  		// 2.결과값 셋팅	    
  		responseData.putFieldMap(record); 
  		
	    return responseData;
	}

	/**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-11-11 09:44:14
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUnpdBondDisProcLstPaging(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		 // 1.쿼리문 호출
		IRecordSet rsReturn = dbSelect("SUnpdBondDisProcLstPaging", requestData, onlineCtx);
		// 2.결과값 셋팅
		responseData.putRecordSet("RS_UNPD_BOND_DIS_PRO_LIST", rsReturn);
	
	    return responseData;
	}

    /**
	 *
	 *
	 * @author 김혁범 (kezmain1)
	 * @since 2015-12-28 09:24:48
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dSUnpdBondDisProcTotSum(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        // 1.쿼리문 호출
        IRecordSet rsReturn = dbSelect("SUnpdBondDisProcTotSum", requestData, onlineCtx);
        // 2.결과값 셋팅
        responseData.putRecordSet("RS_TOT_SUM", rsReturn);
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2016-01-13 17:32:05
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dIUnpdDisSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
        
        IRecordSet unpdRs = requestData.getRecordSet("RS_SLIP_LIST");
       
        Map param = null;
        for(int i=0; i<unpdRs.getRecordCount(); i++){
            param = unpdRs.getRecordMap(i);
            param.put("USER_NO", requestData.getField("USER_NO"));
            dbInsert("IUnpdDisSlip", param, onlineCtx);
        }
    
        return responseData;
    }

    /**
	 *
	 *
	 * @author 장미진 (kuramotojin)
	 * @since 2016-01-13 18:11:36
	 *
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 */
	public IDataSet dDUnpdDisSlip(IDataSet requestData, IOnlineContext onlineCtx) {
        IDataSet responseData = new DataSet();
    
        IRecordSet unpdRs = requestData.getRecordSet("RS_SLIP_LIST");
        
        Map param = null;
        for(int i=0; i<unpdRs.getRecordCount(); i++){
            param = unpdRs.getRecordMap(i);
            
            dbInsert("DUnpdDisSlip", param, onlineCtx);
        }
    
        return responseData;
    }
  
}