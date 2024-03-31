package com.example.form_shamilova

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.form_shamilova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var FIO = binding.FIO
        var age = binding.age
        var phone = binding.phone
        var email = binding.email
        var dog = binding.dog
        var cat = binding.cat
        var parrot = binding.parrot
        var fish = binding.fish
        var choiceRadBut = binding.choiceRadioButton
        var salary = binding.salary
        var volunteer = binding.volunteer
        var inputDesiredSalaryAmount = binding.inputDesiredSalaryAmount
        val sellButton = binding.sellButton

        choiceRadBut.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.salary -> binding.inputDesiredSalaryAmount.visibility = View.VISIBLE
                R.id.volunteer -> binding.inputDesiredSalaryAmount.visibility = View.INVISIBLE
            }
        }

        sellButton.setOnClickListener {
            if (FIO.text.isEmpty() || age.text.isEmpty() || phone.text.isEmpty() || email.text.isEmpty() || (!dog.isChecked && !cat.isChecked && !parrot.isChecked && !fish.isChecked) ||
                (choiceRadBut.checkedRadioButtonId == -1) || (salary.isChecked && inputDesiredSalaryAmount.text.isEmpty())) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
            else if (!isValidPhoneNumber(phone.text.toString().trim())) {
                markError("номера телефона", phone)
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.trim()).matches()) {
                markError("e-mail", email)
            }
            else {
                Toast.makeText(this, "Данные успешно отправлены!", Toast.LENGTH_SHORT).show()
                FIO.isEnabled = false
                age.isEnabled = false
                phone.isEnabled = false
                email.isEnabled = false
                dog.isEnabled = false
                cat.isEnabled = false
                parrot.isEnabled = false
                fish.isEnabled = false
                salary.isEnabled = false
                volunteer.isEnabled = false
                inputDesiredSalaryAmount.isEnabled = false
                sellButton.isEnabled = false
                sellButton.background.setTint(Color.GRAY)
                binding.startAgain.visibility = View.VISIBLE
                binding.finish.visibility = View.VISIBLE
            }
        }
        binding.startAgain.setOnClickListener {
            FIO.text.clear()
            age.text.clear()
            phone.text.clear()
            email.text.clear()
            dog.isChecked = false
            cat.isChecked = false
            parrot.isChecked = false
            fish.isChecked = false
            inputDesiredSalaryAmount.text.clear()
            salary.isChecked = false
            volunteer.isChecked = false
            recreate()
        }
        binding.finish.setOnClickListener {
            System.exit(0)
        }
    }
    // Функция для отображения ошибки.
    private fun markError(input: String, editText: EditText)
    {
        editText.error = "Неверный формат $input"
        editText.background.setTint(Color.RED)
    }
    // Функция для проверки соотвествует ли введенный пользователем номер паттерну.
    private fun isValidPhoneNumber(phone: String): Boolean {
        val phonePattern = "^((8|\\+7)[- ]?)?(\\(?\\d{3}\\)?[- ]?)?\\d{3}[- ]?\\d{2}[- ]?\\d{2}$"
        return phone.matches(Regex(phonePattern))
    }
}