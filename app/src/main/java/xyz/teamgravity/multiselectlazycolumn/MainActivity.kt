package xyz.teamgravity.multiselectlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var items by remember {
                mutableStateOf(
                    (1..20).map {
                        ItemModel(
                            title = "Item $it",
                            selected = false
                        )
                    }
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items.size) { index ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            items = items.mapIndexed { jIndex, model ->
                                if (index == jIndex) model.copy(selected = !model.selected)
                                else model
                            }
                        }
                        .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = items[index].title)
                        if (items[index].selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "selected",
                                tint = Color.Green,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}