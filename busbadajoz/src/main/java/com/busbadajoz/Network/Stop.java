package com.busbadajoz.Network;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name="rows")
public class Stop {

    @Element(name = "row")
    public List<Line> lines;
}
