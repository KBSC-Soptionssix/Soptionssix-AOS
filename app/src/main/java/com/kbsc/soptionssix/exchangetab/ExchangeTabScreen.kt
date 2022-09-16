package com.kbsc.soptionssix.exchangetab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kbsc.soptionssix.R
import com.kbsc.soptionssix.util.component.ExchangeItem
import com.kbsc.soptionssix.util.component.InvisibleGuestToolBar
import com.kbsc.soptionssix.util.theme.PretendardTypography

@Composable
fun ExchangeTabScreen(
    exchangeTabViewModel: ExchangeTabViewModel,
    goExchangeDetail: (String) -> Unit,
    goWriteReview: (String, String, String, String) -> Unit,
    goReadReview: (String?) -> Unit,
    goStoreDetail: (String) -> Unit
) {
    val receiptList: State<List<Receipt>> = exchangeTabViewModel.receiptList.collectAsState()
    MaterialTheme(typography = PretendardTypography) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.view_background))
        ) {
            item { ExchangeTabToolBar() }
            items(receiptList.value) { receipt ->
                Spacer(modifier = Modifier.height(24.dp))
                ExchangeItem(
                    receipt = receipt,
                    review = receipt.review,
                    goExchangeDetail = goExchangeDetail,
                    goWriteReview = goWriteReview,
                    goReadReview = goReadReview,
                    goStoreDetail = goStoreDetail
                )
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun ExchangeTabToolBar() {
    InvisibleGuestToolBar(
        prefixContent = {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp),
                text = stringResource(R.string.exchangeTabReceipt),
                style = MaterialTheme.typography.h1
            )
        }
    )
}
