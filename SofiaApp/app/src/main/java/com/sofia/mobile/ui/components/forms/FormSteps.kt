package com.sofia.mobile.ui.components.forms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sofia.mobile.domain.Etnia
import com.sofia.mobile.domain.Parentesco
import com.sofia.mobile.domain.Sexo
import com.sofia.mobile.ui.components.inputs.CustomDatePicker
import com.sofia.mobile.ui.components.inputs.ImagePicker
import com.sofia.mobile.ui.components.inputs.OutlinedRadioButton
import com.sofia.mobile.ui.components.inputs.OutlinedTextRadioButton
import com.sofia.mobile.ui.theme.BrillantPurple
import com.sofia.mobile.ui.theme.White
import com.sofia.mobile.ui.viewmodels.PatientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInfo(
    pvm: PatientViewModel
){
    val nome by pvm.nome.collectAsState()
    val sobrenome by pvm.sobrenome.collectAsState()
    val etnia by pvm.etnia.collectAsState()


    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            ImagePicker()

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = nome,
                onValueChange = { pvm.updateNome(it) },
                label = { Text("Nome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Primeiro Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = sobrenome,
                onValueChange = { pvm.updateSobrenome(it) },
                label = { Text("Sobrenome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Sobrenome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlinedTextRadioButton(
                label = "Sexo",
                options = Sexo.values().toList(),
                pvm = pvm
            )

            CustomDatePicker(pvm = pvm)

            val options = Etnia.values().map { it.name }
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }

            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(264.dp)
                        .menuAnchor(),
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    value = etnia?.let { it.toString() } ?: "",
                    onValueChange = {},
                    label = { Text("Etnia") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrillantPurple,
                        unfocusedBorderColor = BrillantPurple,
                        unfocusedTextColor = BrillantPurple,
                        focusedLabelColor = BrillantPurple
                    )
                )

                ExposedDropdownMenu(
                    modifier = Modifier,
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier,
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                                pvm.updateEtnia(Etnia.valueOf(selectionOption))
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun FormPerfil(
    pvm: PatientViewModel
) {
    val casosFamilia = pvm.casosFamilia.collectAsState()
    val complicacoesGravidez = pvm.complicacoesGravidez.collectAsState()
    val prematuro = pvm.prematuro.collectAsState()

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedRadioButton(
                label = "Nasceu prematuro?",
                options = listOf("Sim", "Não"),
                state = prematuro,
                onOptionSelected = pvm::updatePrematuro
            )

            OutlinedRadioButton(
                label = "Há alguém na família diagnosticado com autismo?",
                options = listOf("Sim", "Não"),
                state = casosFamilia,
                onOptionSelected = pvm::updateCasosFamilia
            )

            OutlinedRadioButton(
                label = "A mãe teve complicações na gravidez?",
                options = listOf("Sim", "Não"),
                state = complicacoesGravidez,
                onOptionSelected = pvm::updateComplicacoesGravidez
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormResponsavel(
    pvm: PatientViewModel
) {
    val nomeResponsavel by pvm.nomeResponsavel.collectAsState()
    val sobrenomeResponsavel by pvm.sobrenomeResponsavel.collectAsState()
    val parentesco by pvm.parentesco.collectAsState()
    val celularResponsavel by pvm.celular.collectAsState()
    val emailResponsavel by pvm.email.collectAsState()

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = nomeResponsavel,
                onValueChange = { pvm.updateNomeResponsavel(it) },
                label = { Text("Nome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Primeiro Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = sobrenomeResponsavel,
                onValueChange = { pvm.updateSobrenomeResponsavel(it) },
                label = { Text("Sobrenome") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(text = "Sobrenome")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )

            val options = Parentesco.values().map { it.descricao }
            var expanded by remember { mutableStateOf(false) }
            val selectedOptionText by pvm.selectedOptionText.collectAsState()

            ExposedDropdownMenuBox(
                modifier = Modifier,
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(264.dp)
                        .menuAnchor(),
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp),
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("Parentesco") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrillantPurple,
                        unfocusedBorderColor = BrillantPurple,
                        unfocusedTextColor = BrillantPurple,
                        focusedLabelColor = BrillantPurple
                    )
                )

                ExposedDropdownMenu(
                    modifier = Modifier,
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier,
                            text = { Text(selectionOption) },
                            onClick = {
                                pvm._selectedOptionText.value = selectionOption
                                expanded = false
                                pvm.updateParentesco(Parentesco.values().first { it.descricao == selectionOption })
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = celularResponsavel,
                onValueChange = { pvm.updateCelular(it) },
                label = { Text("Celular") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )
            OutlinedTextField(
                modifier = Modifier.width(264.dp),
                value = emailResponsavel,
                onValueChange = { pvm.updateEmail(it) },
                label = { Text("Email") },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BrillantPurple,
                    unfocusedBorderColor = BrillantPurple,
                    unfocusedTextColor = BrillantPurple,
                    focusedLabelColor = BrillantPurple
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

fun isFormInfoValid(pvm: PatientViewModel): Boolean {
    return pvm.nome.value.isNotEmpty() &&
            pvm.sobrenome.value.isNotEmpty() &&
            pvm.dataNascimento.value != null &&
            pvm.sexo.value != null &&
            pvm.etnia.value != null
}

fun isFormPerfilValid(pvm: PatientViewModel): Boolean {
    return pvm.casosFamilia.value != null &&
            pvm.complicacoesGravidez.value != null &&
            pvm.prematuro.value != null
}

fun isFormResponsavelValid(pvm: PatientViewModel): Boolean {
    return pvm.nomeResponsavel.value.isNotEmpty() &&
            pvm.sobrenomeResponsavel.value.isNotEmpty() &&
            pvm.celular.value.isNotEmpty() &&
            pvm.email.value.isNotEmpty() &&
            pvm.parentesco.value != null
}
