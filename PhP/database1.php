<?php

require('fpdf17/fpdf.php');
$con=mysqli_connect('localhost','root','');
mysqli_select_db($con,'sih');
$date = date("Y-m-d");
$status = "Assigned";


class PDF extends FPDF {
	function Header(){
		$this->SetFont('Arial','B',15);
		
		//dummy cell to put logo
		//$this->Cell(12,0,'',0,0);
		//is equivalent to:
		$this->Cell(12);
		
		//put logo
		$this->Image('icongaddhe.png',10,10,10);
		
		$this->Cell(150,10,'GADDHE REPORTS',0,1);
		//$this->Cell(500,10,'Date',0,1);
		
		//dummy cell to give line spacing
		//$this->Cell(0,5,'',0,1);
		//is equivalent to:
		$this->Ln(5);
		
		$this->SetFont('Arial','B',11);
		
		$this->SetFillColor(255, 102, 0);
		$this->SetDrawColor(180,180,255);
		$this->Cell(15,5,'C_Id',1,0,'',true);
		$this->Cell(15,5,'User',1,0,'',true);
		$this->Cell(20,5,'Date',1,0,'',true);
		$this->Cell(25,5,'District',1,0,'',true);
		$this->Cell(35,5,'Contractor',1,0,'',true);
		$this->Cell(30,5,'Status',1,0,'',true);
		$this->Cell(31,5,'Budget',1,0,'',true);
		$this->Cell(20,5,'Deadline',1,1,'',true);		
	
	}
	function Footer(){
		//add table's bottom line
		$this->Cell(190,0,'','T',1,'',true);
		
		//Go to 1.5 cm from bottom
		$this->SetY(-15);
				
		$this->SetFont('Arial','',8);
		
		//width = 0 means the cell is extended up to the right margin
		$this->Cell(0,10,'Page '.$this->PageNo()." / {pages}",0,0,'C');
	}
}


//A4 width : 219mm
//default margin : 10mm each side
//writable horizontal : 219-(10*2)=189mm

$pdf = new PDF('P','mm','A4'); //use new class

//define new alias for total page numbers
$pdf->AliasNbPages('{pages}');

$pdf->SetAutoPageBreak(true,15);
$pdf->AddPage();

$pdf->SetFont('Arial','',9);
$pdf->SetDrawColor(180,180,255);

$query=mysqli_query($con,"select * from citizen_data where status = '$status'");
while($data=mysqli_fetch_array($query)){
	$pdf->Cell(15,5,$data['id'],'LR',0);
	$pdf->Cell(15,5,$data['user'],'LR',0);
	$pdf->Cell(20,5,$data['date'],'LR',0);
	$pdf->Cell(25,5,$data['dist'],'LR',0);
	$pdf->Cell(35,5,$data['contr'],'LR',0);
	$pdf->Cell(30,5,$data['status'],'LR',0);
	$pdf->Cell(31,5,$data['budget'],'LR',0);
	$pdf->Cell(20,5,$data['deadline'],'LR',1);
}














$pdf->Output();
?>
