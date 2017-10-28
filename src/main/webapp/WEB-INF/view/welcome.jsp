<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	</head>
	<body style="background: linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ), url(/images/sarang-bg.png); background-size: cover;">
		<div class="container">
			<div class="row">
				<div class="col-xs-12" style="position: relative;color: whitesmoke;top: 250px;left: 420px;">
	    			<form method="post" class="form-group" action="/format.do" enctype="multipart/form-data">
	    				<label class="custom-file" style="margin-bottom:10px;width: 300px;">
						<input type="file" id="file" name="file" class="custom-file-input">
						<span class="custom-file-control"></span>
						</label><br/>
		    			<input type="submit" value="Format" style="width: 300px;height: 40px;background-color: #85ce57;border-color: #85ce57;" class="btn btn-primary">
	    			</form>
				</div>
			</div>
		</div>
	</body>
</html>