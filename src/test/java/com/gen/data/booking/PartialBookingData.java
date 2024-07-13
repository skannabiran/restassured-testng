package com.gen.data.booking;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartialBookingData {
    String firstname;
    int totalprice;
}