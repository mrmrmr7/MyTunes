<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/12/2019
  Time: 11:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class="col-md-4">
        <div class="card">
            <div class="card-header">
                <strong class="card-title">Current balance</strong>
            </div>
            <div class="card-body">
                <p class="card-text">Your current balance in EUR is: <b>${balance}</b></p>
            </div>
            <div class="card-body">

                <label class="switch switch-text switch-primary switch-pill"><input type="checkbox" class="switch-input"
                                                                                    checked="true"> <span data-on="eur"
                                                                                                          data-off="eur"
                                                                                                          class="switch-label"
                                                                                                          style="background-color: black"></span>
                    <span class="switch-handle"></span></label>

                <label class="switch switch-text switch-secondary switch-pill"><input type="checkbox"
                                                                                      class="switch-input"> <span
                        data-on="usd" data-off="usd" class="switch-label" style="background-color: black"></span> <span
                        class="switch-handle"></span></label>

                <label class="switch switch-text switch-success switch-pill"><input type="checkbox"
                                                                                    class="switch-input"> <span
                        data-on="rus" data-off="rus" class="switch-label" style="background-color: black"></span> <span
                        class="switch-handle"></span></label>

                <label class="switch switch-text switch-warning switch-pill"><input type="checkbox"
                                                                                    class="switch-input"> <span
                        data-on="bln" data-off="bln" class="switch-label" style="background-color: black"></span> <span
                        class="switch-handle"></span></label>

            </div>
        </div>
    </div>
