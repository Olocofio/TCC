<?php
require '../PHPMailer/src/PHPMailer.php';
require '../PHPMailer/src/SMTP.php';
require '../PHPMailer/src/Exception.php';

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

// Função para enviar o e-mail
function EnviaEmail ( $pEmailDestino, $pAssunto, $pHtml, 
                        $pUsuario = "marcelocabello@projetoscti.com.br", 
                        $pSenha = "MarceloC@belo", 
                        $pSMTP = "smtp.projetoscti.com.br" )  {    
   try {
 
     //cria instancia de phpmailer
     $mail = new PHPMailer(); 
     $mail->IsSMTP();  // diz ao php que é o servidor eh SMTP
  
     // servidor smtp
     $mail->Host = $pSMTP;  // configura o servidor
     $mail->SMTPAuth = true;      // requer autenticacao com o servidor                         
     
     $mail->SMTPSecure = 'tls';  // nivel de seguranca                           
     $mail-> SMTPOptions = array ( 'ssl' => array ( 'verificar_peer' => false, 'verify_peer_name' => false,
       'allow_self_signed' => true ) );
      
     $mail->Port = 587;  // porta do serviço no servidor     
      
     $mail->Username = $pUsuario; 
     $mail->Password = $pSenha; 
     $mail->From = $pUsuario; 
     $mail->FromName = "Suporte de senhas"; 
  
     $mail->AddAddress($pEmailDestino, "Usuario"); 
     $mail->IsHTML(true); // o conteudo enviado eh html (poderia ser txt comum sem formato)
     $mail->Subject = $pAssunto; 
     $mail->Body = $pHtml;
     $enviado = $mail->Send(); // disparo
       
     if (!$enviado) {
        echo "<br>Erro: " . $mail->ErrorInfo;
     }
     return $enviado;         
      
   } catch (phpmailerException $e) {
     echo $e->errorMessage(); // erros do phpmailer
   } catch (Exception $e) {
     echo $e->getMessage(); // erros da aplicaï¿½ï¿½o - gerais
   }      
  }
?>