<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/12/2019
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="row">
    <div class="col-md-6 offset-md-3 mr-auto ml-auto">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Credit Card</strong>
            </div>
            <div class="card-body">
                <!-- Credit Card -->
                <div id="pay-invoice">
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Pay Invoice</h3>
                        </div>
                        <hr>
                        <form action="${pageContext.request.contextPath}/crud" method="post" id="updateBalanceForm" novalidate="novalidate">
                            <div class="form-group">
                                <label for="cc-pament" class="control-label mb-1">Payment amount</label>
                                <input id="cc-pament" name="paymentCount" type="text" class="form-control" aria-required="true" aria-invalid="false" value="100">
                            </div>
                            <div class="form-group has-success">
                                <label for="cc-name" class="control-label mb-1">Name on card</label>
                                <input id="cc-name" name="paymentNameOnCard" type="text" class="form-control cc-name valid" data-val="true" data-val-required="Please enter the name on card" autocomplete="cc-name" aria-required="true" aria-invalid="false" aria-describedby="cc-name-error">
                                <span class="help-block field-validation-valid" data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
                            </div>
                            <div class="form-group">
                                <label for="cc-number" class="control-label mb-1">Card number</label>
                                <input id="cc-number" name="paymentCardNumber" type="tel" class="form-control cc-number identified visa" value="" data-val="true" data-val-required="Please enter the card number" data-val-cc-number="Please enter a valid card number" autocomplete="cc-number">
                                <span class="help-block" data-valmsg-for="cc-number" data-valmsg-replace="true"></span>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-group">
                                        <label for="cc-exp" class="control-label mb-1">Expiration</label>
                                        <input id="cc-exp" name="paymentCardExpiration" type="tel" class="form-control cc-exp" value="" data-val="true" data-val-required="Please enter the card expiration" data-val-cc-exp="Please enter a valid month and year" placeholder="MM / YY" autocomplete="cc-exp">
                                        <span class="help-block" data-valmsg-for="cc-exp" data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <label for="x_card_code" class="control-label mb-1">Security code</label>
                                    <div class="input-group">
                                        <input id="x_card_code" name="paymentCardSecurityCode" type="tel" class="form-control cc-cvc" value="" data-val="true" data-val-required="Please enter the security code" data-val-cc-cvc="Please enter a valid security code" autocomplete="off">
                                        <div class="input-group-addon">
                                            <span class="fa fa-question-circle fa-lg" data-toggle="popover" data-container="body" data-html="true" data-title="Security Code" data-content="<div class='text-center one-card'>The 3 digit code on back of the card..<div class='visa-mc-cvc-preview'></div></div>" data-trigger="hover"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button id="payment-button" type="submit" form="updateBalanceForm" class="btn btn-lg btn-info btn-block">
                                    <span id="payment-button-amount">Pay $100.00</span>
                                </button>
                            </div>
                            <input type="hidden" name="command" value="updateBalance">
                        </form>
                    </div>
                </div>

            </div>
        </div> <!-- .card -->

    </div>
</div>
