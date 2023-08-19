package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      LemonadeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          LemonApp()
        }
      }
    }
  }
}

@Composable
fun LemonApp() {
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
  ) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface {
      when (currentStep) {
        1 -> LemonadeTextAndImage(
          R.drawable.lemon_tree,
          R.string.lemon_tree_content_description,
          R.string.lemon_tree_select
        ) {
          currentStep = 2
          squeezeCount = (2..4).random()
        }

        2 -> LemonadeTextAndImage(
          R.drawable.lemon_squeeze,
          R.string.lemon_content_description,
          R.string.lemon_select
        ) {
          squeezeCount--
          if (squeezeCount == 0) {
            currentStep = 3
          }
        }

        3 -> LemonadeTextAndImage(
          R.drawable.lemon_drink,
          R.string.glass_of_lemonade_select,
          R.string.glass_of_lemonade_content_description
        ) { currentStep = 4 }

        4 -> LemonadeTextAndImage(
          R.drawable.lemon_restart,
          R.string.empty_glass_select,
          R.string.empty_glass_content_description
        ) { currentStep = 1 }
      }
    }
  }
}

@Composable
fun LemonadeTextAndImage(
  painter: Int,
  contentDescription: Int,
  textSelect: Int,
  onClick: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    Button(
      onClick = onClick,
      shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
      )
    ) {
      Image(
        painter = painterResource(painter),
        contentDescription = stringResource(contentDescription),
        modifier = Modifier
          .width(dimensionResource(R.dimen.button_image_width))
          .height(dimensionResource(R.dimen.button_image_height))
          .padding(dimensionResource(R.dimen.button_interior_padding))
      )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
    Text(
      text = stringResource(textSelect),
      style = MaterialTheme.typography.bodyLarge
    )
  }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
  LemonadeTheme {
    LemonApp()
  }
}
