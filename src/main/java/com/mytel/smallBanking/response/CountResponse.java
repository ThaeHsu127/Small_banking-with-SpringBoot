package com.mytel.smallBanking.response;

import com.mytel.smallBanking.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountResponse {
   private double count;
}
