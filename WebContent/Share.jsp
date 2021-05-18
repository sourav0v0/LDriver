<%@page import="dao.UserDaoImpl"%>
<%@page import="pojo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
UserDaoImpl udi=new UserDaoImpl();
List<User> user=udi.getUsers();
%>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1> Share page for <%= request.getParameter("name") %> </h1>
<div class="container">
<form action="FileController" method="post"> 	<!-- FileController -->
<input type="hidden" name="action" value="shareType">
<input type="hidden" name="fid" value="<%=request.getParameter("fid")%>">
<input type="hidden" name="usersShare" id="usersShare">
<label >Choose Type</label>  		
 <div class="mb-3 row">
     	<div class="col-sm-6">
     	<input type="radio" class="btn-check btn-outline-primary" value="public" name="type" id="public" onClick="hide()"autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="public" >public</label>
			<input type="radio" class="btn-check btn-outline-primary" value="private" name="type" onClick="unhide()" id="private" autocomplete="off" checked>
			<label class="btn btn-outline-primary" for="private" >private</label>
		</div>
 </div>
<div class="hide" id="hidden">	 
	<div class="mb-3 row">
	   <div class="col-sm-6">
		<label for="user">Choose a Person to Share</label>
	   </div>
	</div>
	 <div class="mb-3 row">
	  	<label for="email" class="col-sm-2 col-form-label">User Email</label>
        <div class="col-sm-6">
          <input type="email" id="Mail" name="email" placeholder="email@example.com" class="form-control-plaintext rounded border border-dark" autocomplete=true>
        </div>
		<div class="col-sm-6">
			<button class="btn btn-outline-primary" type="button" onClick="addElementFromText()">Add User From Field</button>
		</div>
	  </div>
	  <div class="mb-3 row">
	  	<div class="col-sm-6">
	  	 <select class="form-select" name="privateName" id="privateName" >
		    <option selected>Choose Someone</option>
			<%for(int i=0;i<user.size();i++) {%>
			<option value="<%=user.get(i).getEmail() %>"><%=user.get(i).getEmail() %></option>
			<%}%>
		</select>
		</div>
		<div class="col-sm-6">
			<button class="btn btn-outline-primary" type="button" onClick="addElement()">Add</button>
		</div>
	  </div>
	  <div id="AddUser"></div>
</div>
<button class="btn  btn-outline-success">Share Folder </button>
</form>
</div>

<script type="text/javascript">
var autoList=[];
var hd= document.getElementById("hidden");
function init(){
<%for(User u:user){%>
autoList.push("<%=u.getEmail()%>");
<%}%>
hd.style.display = "none";
}
init();
$(function() {
    $( "#Mail" ).autocomplete({
      source: autoList
    });
  });
var arr=[];
function unhide(){
	hd.style.display = "block";
}
function hide(){
	hd.style.display = "none";
}
function addElement(){
	var value=document.getElementById("privateName").value;
	console.log(value === 'Choose Someone')
	if(value === 'Choose Someone' || arr.indexOf(value) >=0)
		return ;
	var addElement=document.getElementById('AddUser');
	var row = document.createElement("div");
	row.classList.add("mb-3"); 
	row.classList.add("row");
	row.classList.add("border");
	row.classList.add("border-primary");
	var col = document.createElement("div"); 
	col.innerHTML=value;
	col.classList.add("col-sm-6");
	col.setAttribute('id',value);
	var button = document.createElement("button"); 
	button.innerHTML="Remove";
	button.setAttribute('onClick','removeItem("'+value+'")');
	button.setAttribute('type','button');
	button.setAttribute('class','col-sm-6');
	row.appendChild(col);
	row.appendChild(button);
	addElement.appendChild(row);
	arr.push(value);
	var hidden=document.getElementById('usersShare');	
	var pre=hidden.value;
	pre= pre+""+value+"-";
	hidden.value=pre;
}
function addElementFromText(){
	var value=document.getElementById("Mail").value;
	if(autoList.indexOf(value) == -1)
		{
		alert("Wrong mail Id");
		return;
		}
	if(arr.indexOf(value) >=0)
		return ;
	var addElement=document.getElementById('AddUser');
	var row = document.createElement("div");
	row.classList.add("mb-3"); 
	row.classList.add("row");
	row.classList.add("border");
	row.classList.add("border-primary");
	var col = document.createElement("div"); 
	col.innerHTML=value;
	col.classList.add("col-sm-6");
	col.setAttribute('id',value);
	var button = document.createElement("button"); 
	button.innerHTML="Remove";
	button.setAttribute('onClick','removeItem("'+value+'")');
	button.setAttribute('type','button');
	button.setAttribute('class','col-sm-6');
	row.appendChild(col);
	row.appendChild(button);
	addElement.appendChild(row);
	arr.push(value);
	var hidden=document.getElementById('usersShare');	
	var pre=hidden.value;
	pre= pre+""+value+"-";
	hidden.value=pre;
}
function removeItem(ritem){
	arr = arr.filter(function(item) {
	    return item !== ritem
	});
	var addElement=document.getElementById('AddUser');
	addElement.innerHTML="";
	var hidden=document.getElementById('usersShare');
	hidden.value="";
	var value="";
	 arr.forEach(function(value){ 
		var row = document.createElement("div");
		row.classList.add("mb-3"); 
		row.classList.add("row");
		row.classList.add("border");
		row.classList.add("border-primary");
		var col = document.createElement("div"); 
		col.innerHTML=value;
		col.classList.add("col-sm-6");
		col.setAttribute('id',value);
		var button = document.createElement("button"); 
		button.innerHTML="Remove";
		button.setAttribute('onClick','removeItem("'+value+'")');
		button.setAttribute('type','button');
		row.appendChild(button);
		row.appendChild(col);
		addElement.appendChild(row);
		var pre=hidden.value;
		pre= pre+""+value+"-";
		hidden.value=pre;
	}); 
}
</script>
</body>
</html>