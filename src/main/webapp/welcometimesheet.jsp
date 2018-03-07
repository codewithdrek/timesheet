<html>
<head>
<style type="text/css">
.button {
    display: block;
    width: 115px;
    height: 25px;
    background: #4E9CAF;
    padding: 10px;
    text-align: center;
    border-radius: 5px;
    color: white;
    font-weight: bold;
}

</style>
</head>
<body>
<table style="width: 100%; border: thick solid; ">
	<tr style="height: 100px; text-align: center; vertical-align: middle;">
		<td colspan="2"> <h2>TIMESHEET MODULE</h2> </td>
	</tr>
	<tr>
		<td><h4>Welcome ${username}</h4> </td>
		<td>  <a class="button" href="http://localhost:8080/sso/logout?token=${token}">Logout</a> </td>
	</tr>
	<tr>
		<td>You are having role</td>
		<td>${role}</td>
	</tr>
	<tr>
		<td colspan="2"  style="height: 150px; text-align: center; vertical-align: middle;"> HERE YOU CAN LOG YOUR TIMESHEET.</td>
	</tr>
	<tr>
		<td> <h5>To go back to your allowed modules access list, click on this button -> </h5></td>
		<td> <a class="button" href="http://localhost:8080/sso/welcome">SSO Welcome</a> </td>
	</tr>
</table>
</body>
</html>