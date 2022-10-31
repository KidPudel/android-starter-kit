```xml
<?xml version="1.0" encoding="utf-8"?>

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:theme="@style/AppTheme">

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="50dp"
        android:layout_marginStart="10dp"
        android:layout_marginEnd="10dp"
        android:inputType="none"
        android:text="@string/calculator_hint"
        android:hint="@string/calculator_hint"
        android:textAlignment="textEnd"
        android:textSize="50sp"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintBottom_toTopOf="@id/clearButton"
        app:layout_constraintGuide_percent="0.3"
        app:layout_constraintTop_toBottomOf="@+id/editText" />

    <Button
        android:id="@+id/clearButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/clear_button"
        android:textSize="20sp"
        android:layout_margin="5dp"
        app:layout_constraintHorizontal_weight="2"
        app:layout_constraintBottom_toTopOf="@id/button7"
        app:layout_constraintTop_toTopOf="@+id/guideline"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/divideButton"/>

    <Button
        android:id="@+id/divideButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/divide_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintHorizontal_weight="1"
        app:layout_constraintBottom_toBottomOf="@id/clearButton"
        app:layout_constraintTop_toTopOf="@+id/clearButton"
        app:layout_constraintStart_toEndOf="@id/clearButton"
        app:layout_constraintEnd_toEndOf="parent"/>

    <Button
        android:id="@+id/button7"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_7"
        android:textSize="20sp"
        android:layout_margin="5dp"
        app:layout_constraintBottom_toTopOf="@id/button4"
        app:layout_constraintTop_toBottomOf="@id/clearButton"
        app:layout_constraintEnd_toStartOf="@id/button8"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button8"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_8"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button7"
        app:layout_constraintTop_toTopOf="@id/button7"
        app:layout_constraintStart_toEndOf="@id/button7"
        app:layout_constraintEnd_toStartOf="@id/button9"/>

    <Button
        android:id="@+id/button9"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_9"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button7"
        app:layout_constraintTop_toTopOf="@id/button7"
        app:layout_constraintStart_toEndOf="@id/button8"
        app:layout_constraintEnd_toStartOf="@id/multiplyButton"/>

    <Button
        android:id="@+id/multiplyButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/multiply_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button7"
        app:layout_constraintTop_toTopOf="@id/button7"
        app:layout_constraintStart_toEndOf="@id/button9"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/button4"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_4"
        android:textSize="20sp"
        android:layout_margin="5dp"
        app:layout_constraintBottom_toTopOf="@id/button1"
        app:layout_constraintTop_toBottomOf="@id/button7"
        app:layout_constraintEnd_toStartOf="@id/button5"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button5"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_5"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button4"
        app:layout_constraintTop_toTopOf="@id/button4"
        app:layout_constraintStart_toEndOf="@id/button4"
        app:layout_constraintEnd_toStartOf="@id/button6"/>

    <Button
        android:id="@+id/button6"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_6"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button4"
        app:layout_constraintTop_toTopOf="@id/button4"
        app:layout_constraintStart_toEndOf="@id/button5"
        app:layout_constraintEnd_toStartOf="@id/subtractButton"/>

    <Button
        android:id="@+id/subtractButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/subtract_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button4"
        app:layout_constraintTop_toTopOf="@id/button4"
        app:layout_constraintStart_toEndOf="@id/button6"
        app:layout_constraintEnd_toEndOf="parent"/>

    <Button
        android:id="@+id/button1"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_1"
        android:textSize="20sp"
        android:layout_margin="5dp"
        app:layout_constraintBottom_toTopOf="@id/button0"
        app:layout_constraintTop_toBottomOf="@id/button4"
        app:layout_constraintEnd_toStartOf="@id/button2"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/button2"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_2"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button1"
        app:layout_constraintTop_toTopOf="@id/button1"
        app:layout_constraintStart_toEndOf="@id/button1"
        app:layout_constraintEnd_toStartOf="@id/button3" />


    <Button
        android:id="@+id/button3"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_3"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button1"
        app:layout_constraintTop_toTopOf="@id/button1"
        app:layout_constraintEnd_toStartOf="@id/addButton"
        app:layout_constraintStart_toEndOf="@id/button2" />


    <Button
        android:id="@+id/addButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/add_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintBottom_toBottomOf="@id/button1"
        app:layout_constraintTop_toTopOf="@id/button1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/button3" />

    <Button
        android:id="@+id/button0"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/button_0"
        android:textSize="20sp"
        android:layout_margin="5dp"
        app:layout_constraintHorizontal_weight="3"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@id/button1"
        app:layout_constraintEnd_toStartOf="@id/dotButton"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/dotButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/dot_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintHorizontal_weight="1"
        app:layout_constraintBottom_toBottomOf="@id/button0"
        app:layout_constraintTop_toTopOf="@id/button0"
        app:layout_constraintEnd_toStartOf="@id/equalButton"
        app:layout_constraintStart_toEndOf="@id/button0" />

    <Button
        android:id="@+id/equalButton"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:text="@string/equal_button"
        android:textSize="20sp"
        android:layout_marginEnd="5dp"
        app:layout_constraintHorizontal_weight="1"
        app:layout_constraintBottom_toBottomOf="@id/button0"
        app:layout_constraintTop_toTopOf="@id/button0"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@id/dotButton" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

![image](https://user-images.githubusercontent.com/63263301/199089229-3ac18bfe-9d02-462f-9d84-4dced02708d4.png)

