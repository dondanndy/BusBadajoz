package com.busbadajoz.Network;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

import retrofit2.http.Path;

@Xml
public class Line {

    @PropertyElement(name = "cell0")
    public String stopCode;

    @PropertyElement(name = "cell1")
    public String lineName;

    @PropertyElement(name = "cell2")
    public String distanceLeft;

    @PropertyElement(name = "cell3")
    public String timeLeft;
}
