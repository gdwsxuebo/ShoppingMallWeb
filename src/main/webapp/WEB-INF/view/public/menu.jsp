<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="left">
    <ul class="menu" id="menu">
        <li class="logo"><a href="web/xfmall/getXfMall"><img src="new/image/logo_menu.png" alt=""></a></li>
        <c:forEach items="${menuTreeList }" var="menu">
        	<c:choose>	
	        	<c:when test="${menu.childrenList!=null}">
		        	<c:choose>
		        		<c:when test="${'setPromotion' eq select || 'getPsStatistics' eq select || 'ordinaryPromotion' eq select || 'getPromotionCheck' eq select}">
				        	<li class="icon sale active">
		        		</c:when>
		        		<c:otherwise>
				        	<li class="icon sale">
		        		</c:otherwise>
		        	</c:choose>
	                    <a href="${menu.urladdress}"><i></i>促销</a><i></i>
	                    <ul class="sonMenu">
	                    	<c:forEach items="${menu.childrenList }" var="child">
	                    		<c:choose>
	                    			<c:when test="${child.note eq select}">
			                        	<li class="active"><a href="${child.urladdress}">${child.name}</a></li>
	                    			</c:when>
	                    			<c:otherwise>
			                        	<li class=""><a href="${child.urladdress}">${child.name}</a></li>
	                    			</c:otherwise>
	                    		</c:choose>
	                        </c:forEach>
	                    </ul>
	                </li>
	        	</c:when>
	        	<c:otherwise>
	        		<c:choose>  
	        			<c:when test="${menu.note eq select}">
			        		<li class="${menu.icon} active"><a href="${menu.urladdress}"><i></i>${menu.name}</a></li>
	        			</c:when>
	        			<c:otherwise>
	        				<li class="${menu.icon}"><a href="${menu.urladdress}"><i></i>${menu.name}</a></li>
	        			</c:otherwise>
	        		</c:choose>
	        	</c:otherwise>
        	</c:choose>
        </c:forEach>
    </ul>
</div>
<script src="new/js/jquery-1.11.2.js"></script>
<script src="new/js/pos.js"></script>
<script src="new/js/echarts-3.2.3.js"></script>


