<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review</title>
<style type="text/css">
    table { border: 0; }
    table td { padding: 5px; }
</style>
</head>
<body>
<div align="center">
    <h1>Please Review Before Paying</h1>
    <form action="execute_payment" method="post">
    <table>
        <tr>
            <td colspan="2"><b>Transaction Details:</b></td>
            <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>${transaction.description}</td>
        </tr>
        <tr>
            <td>Subtotal:</td>
            <td>${orderDetail.getSubtotal()} Rs</td>
        </tr>
        <tr>
            <td>Shipping:</td>
            <td>${orderDetail.getShipping()} Rs</td>
        </tr>
        <tr>
            <td>Tax:</td>
            <td>${orderDetail.getTax()} Rs</td>
        </tr>
        <tr>
            <td>Total:</td>
            <td>${orderDetail.getTotal())} Rs</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Payer Information:</b></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td>Mahadevan</td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td>A S</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td>mahadevan98sekar@gmail.com</td>
        </tr>
        <tr><td><br/></td></tr>
        <tr>
            <td colspan="2"><b>Shipping Address:</b></td>
        </tr>
        <tr>
            <td>Recipient Name:</td>
            <td>Mahadevan</td>
        </tr>
        <tr>
            <td>Line 1:</td>
            <td>45 Ambattur</td>
        </tr>
        <tr>
            <td>City:</td>
            <td>Chennai</td>
        </tr>
        <tr>
            <td>State:</td>
            <td>Tamil Nadu</td>
        </tr>
        <tr>
            <td>Country:</td>
            <td>India</td>
        </tr>
        <tr>
            <td>Postal Code:</td>
            <td>600017</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Pay Now" />
            </td>
        </tr>    
    </table>
    </form>
</div>
</body>
</html>