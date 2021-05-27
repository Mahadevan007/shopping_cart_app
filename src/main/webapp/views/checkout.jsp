<form action='/charge' method='POST' id='checkout-form'>
    <input type='hidden' th:value='${amount}' name='amount' />
    <label>Price:<span th:text='${amount/100}' /></label>
    <!-- NOTE: data-key/data-amount/data-currency will be rendered by Thymeleaf -->
    <script
       src='https://checkout.stripe.com/checkout.js' 
       class='stripe-button'
      	data-key="${stripePublicKey}"
       data-name='Shopping Cart'
       data-description='Pay Securely'
       data-image
         ='https://i.pinimg.com/236x/2d/96/4a/2d964a6bf37d9224d0615dc85fccdd62--shopping-cart-logo-info-graphics.jpg'
       data-amount="1"
       data-locale='auto'
       data-zip-code='false'>
   </script>
</form>
<%-- <form action='/charge' method='POST' id='checkout-form'>
  <script
    src="https://checkout.stripe.com/checkout.js"
    class="stripe-button"
    data-key="${stripePublicKey}"
    data-name="Gold Tier"
    data-description="Monthly subscription with 30 days trial"
    data-amount="1"
    data-label="Subscribe">
  </script>
</form> --%>