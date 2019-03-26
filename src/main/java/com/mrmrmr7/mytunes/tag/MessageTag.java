//package com.mrmrmr7.mytunes.tag;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspTagException;
//import javax.servlet.jsp.jstl.fmt.LocalizationContext;
//import javax.servlet.jsp.tagext.TagSupport;
//
////
////public class MessageTag extends SimpleTagSupport {
////
////    private String message;
////    private LocalizationContext bundle;
////    private String label;
////    private boolean showFlag;
////
////    public String getLabel() {
////        return label;
////    }
////
////    public void setLabel(String label) {
////        this.label = label;
////    }
////
////    public boolean isShowFlag() {
////        return showFlag;
////    }
////
////    public void setShowFlag(boolean showFlag) {
////        this.showFlag = showFlag;
////    }
////
////    public String getMessage() {
////        return message;
////    }
////
////    public void setMessage(String message) {
////        this.message = message;
////    }
////
////    public LocalizationContext getBundle() {
////        return bundle;
////    }
////
////    public void setBundle(LocalizationContext bundle) {
////        this.bundle = bundle;
////    }
////
////
////
////    public void doTag() throws JspException, IOException {
////        getJspContext().getOut().write("Hello, world.");
////    }
////}
//
//public class MessageTag extends TagSupport {
//    private String message;
//    private LocalizationContext bundle;
//    private String label;
//    private boolean showFlag;
//
//    public String getLabel() {
//        return label;
//    }
//
//    public void setLabel(String label) {
//        this.label = label;
//    }
//
//    public boolean isShowFlag() {
//        return showFlag;
//    }
//
//    public void setShowFlag(boolean showFlag) {
//        this.showFlag = showFlag;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public LocalizationContext getBundle() {
//        return bundle;
//    }
//
//    public void setBundle(LocalizationContext bundle) {
//        this.bundle = bundle;
//    }
//
//
//    public int doStartTag() throws JspException {
//        try {
//            pageContext.getOut().println(
//                    "<div class=\"sufee-alert alert with-close alert-dark alert-dismissible fade show\">" +
//                            "<span class=\"badge badge-pill badge-dark\">" + bundle.getResourceBundle().getString(label) + "</span>" +
//                            bundle.getResourceBundle().getString(message) +
//                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">" +
//                                "<span aria-hidden=\"true\">X</span>" +
//                            "</button>" +
//                        "</div>");
//        } catch (Exception e) {
//            throw new JspTagException("Simple tag: " + e.getMessage());
//        }
//        return SKIP_BODY;
//    }
//    public int doEntTag() {
//        return EVAL_PAGE;
//    }
//}