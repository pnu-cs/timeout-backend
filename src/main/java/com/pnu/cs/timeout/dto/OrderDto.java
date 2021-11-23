package com.pnu.cs.timeout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private long userId;
    private Date createdAt;
    private List<OrderDetailsDto> orderDetailsDtoList;
}
