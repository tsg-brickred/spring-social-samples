<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
	<title>Spring Social Showcase: Connected to Facebook</title>
</head>
<body>
<h1>Spring Social Showcase: Connected to Facebook</h1>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>
			Spring Social Showcase is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<a href="<c:url value="/"/>">Return to home page</a>

</body>
</html>
