<?php
  require_once('../db/dbhelp.php');
  if ($_SERVER["REQUEST_METHOD"] == "POST"){
  $u ='';
  $p ='';
  if(isset($_POST["username"])) { $u = $_POST['username']; }
   if(isset($_POST["password"])) { $p = $_POST['password']; }
   $u = str_replace('\'', '\\\'', $u);
  $p      = str_replace('\'', '\\\'', $p);
  $sql = "select * from khachhang where TenDangNhap = '$u' and MatKhau = '$p'";
  $dataLogin = executeResult($sql);
  if ($dataLogin != null && count($dataLogin) > 0) {
  $c = 1;
    header('location: Order');
  }
}
?>
<!DOCTYPE html>
<html>
<head>
  <title>Animated Login Form</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
  <script src="https://kit.fontawesome.com/a81368914c.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
  <img class="wave" src="img/wave.png">
  <div class="container">
    <div class="img">
      <img src="img/bg.svg">
    </div>
    <div class="login-content">
      <form action="" method="POST">
        <img src="img/avatar.svg">
        <h2 class="title">Welcome</h2>
              <div class="input-div one">
                 <div class="i">
                    <i class="fas fa-user"></i>
                 </div>
                 <div class="div">
                    <h5>Username</h5>
                    <input type="text" class="input" name="username">
                 </div>
              </div>
              <div class="input-div pass">
                 <div class="i"> 
                    <i class="fas fa-lock"></i>
                 </div>
                 <div class="div">
                    <h5>Password</h5>
                    <input type="password" class="input" name="password">
                 </div>
              </div>
              <a href="#">Forgot Password?</a>
              <input type="submit" class="btn" value="Login" onclick="checkLogin(1)">
            </form>
        </div>
    </div>
    <!-- <script type="text/javascript">
      function checkLogin(id){
        console.log(id);
        $.post('Order/ajaxOrder.php', {
        'id':id,
        'action':'login'
      }, function (data){
        
      })
      }
    </script> -->
</body>
</html>