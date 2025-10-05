package ru.practicum.jetpackcompost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.practicum.jetpackcompost.ui.theme.JetPackCompostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackCompostTheme {
                ContactColumnPreview()
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContactImage(contact)
        ContactName(contact)
        ContactInfoRow(stringResource(R.string.phone), contact.phone)
        ContactInfoRow(stringResource(R.string.address), contact.address)
        ContactInfoRow(stringResource(R.string.email), contact.email)
    }
}

@Composable
fun ContactImage(contact: Contact) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (contact.imageRes == null) {
            Image(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
            )
            Text(
                style = MaterialTheme.typography.labelLarge,
                fontSize = 16.sp,
                text = "${contact.name.first()}" +
                        "${contact.surname.orEmpty().first()}"
            )
        } else {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = painterResource(id = contact.imageRes),
                contentDescription = null,
            )
        }

    }
}

@Composable
fun ContactName(contact: Contact) {
    Column(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.labelLarge,
            fontSize = 16.sp,
            text = "${contact.name} ${contact.surname.orEmpty()}"
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 20.sp,
                text = contact.familyName
            )
            if (contact.isFavorite) {
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ContactInfoRow(column: String, info: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!info.isNullOrEmpty()) {
            Text(
                modifier = Modifier.weight(1F),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                text = "$column :"
            )
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(start = 8.dp),
                text = info
            )
        }
    }
}

@Preview(name = "portrait", showSystemUi = true, device = "id:Nexus 5")
@Composable
fun ContactColumnPreview() {
    ContactDetails(
        contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            email = "evga@gmail.com",
            isFavorite = true
        )
    )
}

@Preview(name = "portrait", showSystemUi = true, device = "id:Nexus 5")
@Composable
fun ContactColumnPreviewSecond() {
    ContactDetails(
        contact = Contact(
            name = "Василий",
            familyName = "Кузякин",
            phone = "...",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            imageRes = R.drawable.volchara,
            isFavorite = false
        )
    )
}