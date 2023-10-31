package org.zotero.android.uicomponents.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.zotero.android.architecture.prettyPrint
import org.zotero.android.uicomponents.theme.CustomTheme


@Composable
fun RoundBadgeIcon(
    count: Int,
) {
    // Actual badge + content
    var textModifier = Modifier
        .widthIn(min = 16.dp)
        .height(16.dp)
        .background(
            color = CustomTheme.colors.zoteroItemDetailSectionBackground,
            shape = RoundedCornerShape(size = 16.dp)
        )
    textModifier = if (count < 10) {
        textModifier.padding(horizontal = 1.dp, vertical = 1.dp)
    } else {
        textModifier.padding(horizontal = 6.dp, vertical = 1.dp)
    }
    Text(
        text = count.prettyPrint(),
        style = CustomTheme.typography.newCaptionOne,
        color = CustomTheme.colors.primaryContent,
        modifier = textModifier,
        textAlign = TextAlign.Center,
    )

}
