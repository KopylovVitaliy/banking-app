package com.example.bankingapp.dto.account;

import java.util.List;

public record AccountsPageDto(long totalCount,
                              List<AccountInfoDto> accounts) {
}
