<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:directive.attribute name="needShow" required="true" type="java.lang.Boolean"/>
<jsp:directive.attribute name="label" required="true" rtexprvalue="true" type="java.lang.String"/>
<jsp:directive.attribute name="message" required="true" rtexprvalue="true" type="java.lang.String"/>

<c:if test="${needShow}">
    <div class="sufee-alert alert with-close alert-dark alert-dismissible fade show">
        <span class="badge badge-pill badge-dark"><fmt:message key="${label}" bundle="${bundle}"/> </span>
        <fmt:message key="${message}" bundle="${bundle}"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">X</span>
        </button>
    </div>
</c:if>
