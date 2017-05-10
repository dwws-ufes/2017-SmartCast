package br.ufes.inf.nemo.smartcast.domain.podcast;

import org.dom4j.Element;
import org.dom4j.QName;

public class ITunesItemInfo extends ITunesInfo {

    private String duration;
    private Boolean isClosedCaptioned;
    private Integer order;

    public ITunesItemInfo(Element parent) {
        super(parent);
    }

    public String getDuration() {
        if (this.duration != null) {
            return this.duration;
        }

        Element durationElement = this.parent.element(QName.get("duration", this.iTunesNamespace));
        if (durationElement == null) {
            return null;
        }

        return this.duration = durationElement.getText();
    }

    public boolean isClosedCaptioned() {
        if (this.isClosedCaptioned != null) {
            return this.isClosedCaptioned;
        }

        Element isClosedCaptionedElement = this.parent.element(QName.get("isClosedCaptioned", this.iTunesNamespace));
        if (isClosedCaptionedElement == null) {
            return this.isClosedCaptioned = false;
        }

        if ("yes".equalsIgnoreCase(isClosedCaptionedElement.getTextTrim())) {
            return this.isClosedCaptioned = true;
        }

        return this.isClosedCaptioned = false;
    }

    public Integer getOrder() {
        if (this.order != null) {
            return this.order;
        }

        Element orderElement = this.parent.element(QName.get("order", this.iTunesNamespace));
        if (orderElement == null) {
            return null;
        }

        try {
            return this.order = Integer.parseInt(orderElement.getTextTrim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
