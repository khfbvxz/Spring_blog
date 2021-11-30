<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<script src="${pageContext.request.contextPath}/resources/js/login_check.js"></script>

	<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars"></i>
        </button>
        
         
        <div class="collapse navbar-collapse" id="navbarResponsive">
         <ul class="navbar-nav">
            <li class="nav-item">
             <a href="index.jsp"> <img src='./resources/upload/home.gif' width=100 height=40> </a>
            </li>
            
            <li class="nav-item">
              <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="list.do">Board</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="album.do">Album</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="signUp.do">SignUp</a>
            </li>
            <li class="nav-item">
              <span id="user_s">
	           <form method="post" action="login.do">
	             <input type="text"  placeholder="id"   name="id" id="id" value=''  required>
	             <input type="text"  placeholder="pw"   name="pw" id="pw" value='1234' required>
	             <input type="button" class="btn btn-primary" onclick="javascript:loginCheck();" value="login">
               </form>
              </span>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
   <!-- Page Header   -->
   <header class="masthead">
     <div class="overlay"></div>
     <div class="container">
       <div class="row">
         <div class="col-lg-8 col-md-10 mx-auto">
           <div class="site-heading">
             <h1>My Spring BBS</h1>
           </div>
         </div>
       </div>
     </div>
   </header>



