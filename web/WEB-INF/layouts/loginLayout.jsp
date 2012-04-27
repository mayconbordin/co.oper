<%@page trimDirectiveWhitespaces="true" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>

<compress:html enabled="true"
               compressJavaScript="true"
               jsCompressor="closure"
               removeComments="true"
               compressCss="true"
               removeIntertagSpaces="true"
               >

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><tiles:insertAttribute name="mainTitle" ignore="true" /> - <tiles:insertAttribute name="title" ignore="true" /></title>
	<link rel="stylesheet" href="${root}/css/style_login.css" type="text/css" media="screen" title="default" />

        <!-- jQuery headers -->
        <sj:head jqueryui="true" />

        <script type="text/javascript" src="${root}/js/mascara.js"></script>
    </head>

    <body>
	<div class="headerlogo">
            <img src="${root}/images/login_logo.png"/>
	</div>

	<tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </body>
</html>
</compress:html>