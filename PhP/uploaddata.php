<?php
require_once 'connect.php';

$user = $_POST['userId'];
$lat = $_POST['lat'];
// $dLat = (double)$lat;
$lon = $_POST['lon'];
// $dLon = (double)$lon;
$dist = $_POST['dist'];
$near = $_POST['near'];
$sugg = $_POST['sugg'];
$sts = 'Sucessfully Submitted';
$date = date("Y-m-d");
$dead = date("Y-m-d");
$contr = "Not Assigned";
$budg = "0";
$lod = -1;
$target_dir = "image/";
$target_file = $target_dir.$user.time().basename($_FILES["image"]["name"]);
$uploadOk = 1;
$target_dir1 = "/image/";
$target_file1 = $target_dir1.$user.time().basename($_FILES["image"]["name"]);
$url = "vektor.soumit.tech:80".$target_file1;
//$url = "abc";
$response = array();
$error = "";
$check = getimagesize($_FILES["image"]["tmp_name"]);
if($check != false){
        $uploadOk = 1;
}
else{
        $uploadOk = 0;
        $error .= "Not a valid image.";
}
if($uploadOk == 0){
        $response['status'] = 0;
        $response['message'] = $error;
}else{
                
                $sql = "INSERT INTO citizen_data(date, user, lat, lon, land, dist, sug, url, lod, contr, status, budget, deadline) VALUES ('$date','$user','$lat','$lon','$near','$dist','$sugg','$url','$lod','$contr','$sts','$budg','$dead')";
                mysqli_query($conn,$sql);
        if(move_uploaded_file($_FILES["image"]["tmp_name"], $target_file)){
                $response['status'] = 1;
                $response['message'] = "Image Uplaoded Sucessfully";
        }else{
                $response['status'] = 0;
                $response['message'] = "Unable To Upload To Server";
        }
}
echo json_encode($response);
?>
