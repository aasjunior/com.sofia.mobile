package com.sofia.mobile.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Parentesco
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.models.PacienteModel
import com.sofia.mobile.models.ResponsavelModel
import com.sofia.mobile.ui.components.inputs.ImagePicker
import com.sofia.mobile.ui.components.text.body1
import com.sofia.mobile.ui.components.text.h3
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.White
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun PatientProfileCard(
    paciente: PacienteModel
){
    ElevatedCard(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = White,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(1.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ){
            ImagePicker()

            Column {
                Text(
                    text = "${paciente.nome} ${paciente.sobrenome}",
                    style = h3.copy(BrillantPurple)
                )
                Text(
                    text = "Alguma informação",
                    style = body1.copy(Gray)
                    )
            }
            Column(

            ){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Menu",
                        tint = BrillantPurple
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PatientProfileCardPreview(){
    val responsavel = ResponsavelModel(
        id = 1,
        nome = "Paula",
        sobrenome = "Santos",
        parentesco = Parentesco.MAE.descricao,
        telefone = "(13) 98765-4321",
        email = "paula@email.com"
    )
    val paciente = PacienteModel(
        id = 1,
        nome = "Luana",
        sobrenome = "Santos",
        sexo = Sexo.FEMININO,
        dataNascimento = LocalDate.of(2018, 12, 12),
        etnia = Etnia.PARDA,
        casosFamilia = true,
        complicacoesGravidez = true,
        prematuro = true,
        responsavel = responsavel,
        dataCadastro = LocalDateTime.now()
    )
    PatientProfileCard(paciente)
}