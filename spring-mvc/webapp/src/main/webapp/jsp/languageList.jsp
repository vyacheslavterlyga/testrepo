<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ page import="org.springframework.context.MessageSource"%>
<%@ page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.lang.String"%>

<%
  MessageSource messageSource = (MessageSource) RequestContextUtils.getWebApplicationContext(request).getBean("messageSource");
  String defaultMessage = messageSource.getMessage("language", null, "DEFAULT", request.getLocale());
  HashMap<String, String> availableLocales = new HashMap<String, String>();
  availableLocales.put(request.getLocale().getLanguage(), defaultMessage);
  for (Locale locale : Locale.getAvailableLocales()) {
    String msg = messageSource.getMessage("language", null, defaultMessage, locale);
    if ( !defaultMessage.equals(msg) && !availableLocales.containsKey(locale.getLanguage())) {
      availableLocales.put(locale.getLanguage(), msg);
    }
  }
  pageContext.setAttribute("availableLocales", availableLocales);
%>
<link rel="stylesheet"
	href="<spring:url value="/css/languageList.css"/>">
<div id="languageList">
	<spring:message code="language.title" /> : <select
		onchange="if (this.value) window.location.href=this.value">
		<c:forEach items="${availableLocales.keySet()}" var="localeKey">
			<c:url value="" var="selectLangUrl">
				<c:forEach items="${paramValues}" var="p">
					<c:choose>
						<c:when test="${p.key == 'language'}">
							<c:param name="language" value="${localeKey}" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${p.value}" var="val">
								<c:param name="${p.key}" value="${val}" />
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${empty param['language'] }">
					<c:param name="language" value="${localeKey}" />
				</c:if>
			</c:url>
			<option value="${selectLangUrl}"
				<c:if test="${localeKey == pageContext.response.locale}">selected</c:if>>${availableLocales[localeKey]}</option>
		</c:forEach>
	</select>
</div>
