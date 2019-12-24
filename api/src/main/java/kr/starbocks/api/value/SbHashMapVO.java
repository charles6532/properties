package kr.starbocks.api.value;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import kr.starbocks.api.domain.SbAskDisclosureDO;
import kr.starbocks.api.domain.SbConsultPropertyDO;
import kr.starbocks.api.domain.SbLocationInfoDO;
import kr.starbocks.api.domain.SbNotificationDO;
import kr.starbocks.api.domain.SbStatementDO;

@Component
public class SbHashMapVO {
	private HashMap<String, SbLocationInfoDO> _SEARCH_CONDITION_RAPMS = null;	// propertiesService searchConditionRapms(), removeHmVO()
	private HashMap<String, SbLocationInfoDO> _SEARCH_CONDITION_SF = null;		// sfService searchProp(),removeHmVO()
	private HashMap<String, SbLocationInfoDO> _MY_PROP_SEARCH_RAPMS = null;		// propertiesService searchMineRapm()
	private HashMap<String, String> _AUTH_INFO_SF = null;						// sfService emailAuthprocess(), verifyAuthenticationWith(), removeHmVO()
	private HashMap<String, String> _AUTH_INFO_RAPMS = null;					// propertiesService removeHmVO()
	private HashMap<String, SbConsultPropertyDO> _MY_PROPERTIES_RAPMS = null;	// propertiesService selectMyProperties()
	private HashMap<String, SbConsultPropertyDO> _ASK_FOR_SF = null;			// sfService askforList()
	private HashMap<String, String> _ASK_PROP_PROPS_IDS_RAPMS = null;			// propertiesService addtoPropsId(), deleteFromPropsId()
	private HashMap<String, String> _ASK_DISCLOSURE_PROP_IDS_SF = null;			// sfService addtoPropsId(), deletefromPropsId()
	private HashMap<String, String> _ASK_DISCLOSURE_PROP_IDS_RAPMS = null;		// askDisclosureService addtoPropsId(), deleteFromPropsId()
	private HashMap<String, SbAskDisclosureDO> _ASK_DISCLOSURE_SF = null;		// sfService askDisclosureList()
	private HashMap<String, SbStatementDO> _SALES_STATEMENT_RAPMS = null;		// salesStatementService salesList()
	private HashMap<String, SbNotificationDO> _NOTIFICATION_LIST_RAPMS = null;	// notificationService
	private HashMap<String, SbNotificationDO> _NOTIFICATION_LIST_SF = null;		// sf notiList()
	
	
	
	
	
	public HashMap<String, SbNotificationDO> get_NOTIFICATION_LIST_SF() {
		return _NOTIFICATION_LIST_SF;
	}
	public void set_NOTIFICATION_LIST_SF(HashMap<String, SbNotificationDO> _NOTIFICATION_LIST_SF) {
		this._NOTIFICATION_LIST_SF = _NOTIFICATION_LIST_SF;
	}
	public HashMap<String, SbNotificationDO> get_NOTIFICATION_LIST_RAPMS() {
		return _NOTIFICATION_LIST_RAPMS;
	}
	public void set_NOTIFICATION_LIST_RAPMS(HashMap<String, SbNotificationDO> _NOTIFICATION_LIST_RAPMS) {
		this._NOTIFICATION_LIST_RAPMS = _NOTIFICATION_LIST_RAPMS;
	}
	public HashMap<String, SbConsultPropertyDO> get_ASK_FOR_SF() {
		return _ASK_FOR_SF;
	}
	public void set_ASK_FOR_SF(HashMap<String, SbConsultPropertyDO> _ASK_FOR_SF) {
		this._ASK_FOR_SF = _ASK_FOR_SF;
	}
	public HashMap<String, SbLocationInfoDO> get_SEARCH_CONDITION_SF() {
		return _SEARCH_CONDITION_SF;
	}
	public void set_SEARCH_CONDITION_SF(HashMap<String, SbLocationInfoDO> _SEARCH_CONDITION_SF) {
		this._SEARCH_CONDITION_SF = _SEARCH_CONDITION_SF;
	}
	public HashMap<String, SbConsultPropertyDO> get_MY_PROPERTIES_RAPMS() {
		return _MY_PROPERTIES_RAPMS;
	}
	public void set_MY_PROPERTIES_RAPMS(HashMap<String, SbConsultPropertyDO> _MY_PROPERTIES_RAPMS) {
		this._MY_PROPERTIES_RAPMS = _MY_PROPERTIES_RAPMS;
	}
	public HashMap<String, String> get_AUTH_INFO_RAPMS() {
		return _AUTH_INFO_RAPMS;
	}
	public void set_AUTH_INFO_RAPMS(HashMap<String, String> _AUTH_INFO_RAPMS) {
		this._AUTH_INFO_RAPMS = _AUTH_INFO_RAPMS;
	}
	public HashMap<String, SbLocationInfoDO> get_SEARCH_CONDITION_RAPMS() {
		return _SEARCH_CONDITION_RAPMS;
	}
	public void set_SEARCH_CONDITION_RAPMS(HashMap<String, SbLocationInfoDO> _SEARCH_CONDITION_RAPMS) {
		this._SEARCH_CONDITION_RAPMS = _SEARCH_CONDITION_RAPMS;
	}
	public HashMap<String, SbLocationInfoDO> get_MY_PROP_SEARCH_RAPMS() {
		return _MY_PROP_SEARCH_RAPMS;
	}
	public void set_MY_PROP_SEARCH_RAPMS(HashMap<String, SbLocationInfoDO> _MY_PROP_SEARCH_RAPMS) {
		this._MY_PROP_SEARCH_RAPMS = _MY_PROP_SEARCH_RAPMS;
	}
	public HashMap<String, String> get_AUTH_INFO_SF() {
		return _AUTH_INFO_SF;
	}
	public void set_AUTH_INFO_SF(HashMap<String, String> _AUTH_INFO_SF) {
		this._AUTH_INFO_SF = _AUTH_INFO_SF;
	}
	public HashMap<String, String> get_ASK_PROP_PROPS_IDS_RAPMS() {
		return _ASK_PROP_PROPS_IDS_RAPMS;
	}
	public void set_ASK_PROP_PROPS_IDS_RAPMS(HashMap<String, String> _ASK_PROP_PROPS_IDS_RAPMS) {
		this._ASK_PROP_PROPS_IDS_RAPMS = _ASK_PROP_PROPS_IDS_RAPMS;
	}
	public HashMap<String, String> get_ASK_DISCLOSURE_PROP_IDS_SF() {
		return _ASK_DISCLOSURE_PROP_IDS_SF;
	}
	public void set_ASK_DISCLOSURE_PROP_IDS_SF(HashMap<String, String> _ASK_DISCLOSURE_PROP_IDS_SF) {
		this._ASK_DISCLOSURE_PROP_IDS_SF = _ASK_DISCLOSURE_PROP_IDS_SF;
	}
	public HashMap<String, String> get_ASK_DISCLOSURE_PROP_IDS_RAPMS() {
		return _ASK_DISCLOSURE_PROP_IDS_RAPMS;
	}
	public void set_ASK_DISCLOSURE_PROP_IDS_RAPMS(HashMap<String, String> _ASK_DISCLOSURE_PROP_IDS_RAPMS) {
		this._ASK_DISCLOSURE_PROP_IDS_RAPMS = _ASK_DISCLOSURE_PROP_IDS_RAPMS;
	}
	public HashMap<String, SbAskDisclosureDO> get_ASK_DISCLOSURE_SF() {
		return _ASK_DISCLOSURE_SF;
	}
	public void set_ASK_DISCLOSURE_SF(HashMap<String, SbAskDisclosureDO> _ASK_DISCLOSURE_SF) {
		this._ASK_DISCLOSURE_SF = _ASK_DISCLOSURE_SF;
	}
	public HashMap<String, SbStatementDO> get_SALES_STATEMENT_RAPMS() {
		return _SALES_STATEMENT_RAPMS;
	}
	public void set_SALES_STATEMENT_RAPMS(HashMap<String, SbStatementDO> _SALES_STATEMENT_RAPMS) {
		this._SALES_STATEMENT_RAPMS = _SALES_STATEMENT_RAPMS;
	}
	@Override
	public String toString() {
		return "SbHashMapVO [_SEARCH_CONDITION_RAPMS=" + _SEARCH_CONDITION_RAPMS + ", _MY_PROP_SEARCH_RAPMS="
				+ _MY_PROP_SEARCH_RAPMS + ", _AUTH_INFO_SF=" + _AUTH_INFO_SF + ", _ASK_PROP_PROPS_IDS_RAPMS="
				+ _ASK_PROP_PROPS_IDS_RAPMS + ", _ASK_DISCLOSURE_PROP_IDS_SF=" + _ASK_DISCLOSURE_PROP_IDS_SF
				+ ", _ASK_DISCLOSURE_PROP_IDS_RAPMS=" + _ASK_DISCLOSURE_PROP_IDS_RAPMS + ", _ASK_DISCLOSURE_SF="
				+ _ASK_DISCLOSURE_SF + ", _SALES_STATEMENT_RAPMS=" + _SALES_STATEMENT_RAPMS + "]";
	}
	
		
}
