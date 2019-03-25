package com.mrmrmr7.mytunes.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import javax.servlet.jsp.tagext.TagSupport;

public class SecondTest extends TagSupport {
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public Object getBundle() {
        return bundle;
    }

    public void setBundle(Object bundle) {
        this.bundle = bundle;
    }

    private Object bundle;

    public int doStartTag() throws JspException {
        try {
            System.out.println("msg");
            pageContext.getOut().println(this.msg);
            LocalizationContext localizationContext = new LocalizationContext();
            String test = ((LocalizationContext) bundle).getResourceBundle().getString("signup.login");
            System.out.println(test);
        } catch (Exception e) {
            throw new JspTagException("Simple tag: " + e.getMessage());
        }
        return SKIP_BODY;
    }
    public int doEntTag() {
        return EVAL_PAGE;
    }
}