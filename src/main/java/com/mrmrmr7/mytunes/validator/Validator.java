package com.mrmrmr7.mytunes.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface Validator {
    boolean validate(HttpServletRequest request);
}
