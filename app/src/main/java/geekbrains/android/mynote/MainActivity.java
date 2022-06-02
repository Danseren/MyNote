package geekbrains.android.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] my_notes = getResources().getStringArray(R.array.my_notes);
        MyNote myNote[] = new MyNote[my_notes.length];

        //Toast.makeText(MainActivity.this, my_notes[1], Toast.LENGTH_SHORT).show();

        for (int i = 0; i < my_notes.length; i++) {

            myNote[i] = new MyNote();
            myNote[i].setName(my_notes[i].substring(0, 5));
            myNote[i].setNoteBody(my_notes[i]);
            //Toast.makeText(MainActivity.this, myNote[i].toString(), Toast.LENGTH_LONG).show();
        }

        StartFragment startFragment = new StartFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_1, startFragment)
                .commit();

        initDrawer();
    }

    private void initToolbarAndDrawer() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initDrawer();
    }

    private void initDrawer() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_drawer_about:
                        showAboutFragment();
                        drawerLayout.close();
                        return true;
                    case R.id.action_drawer_exit:
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                showAboutFragment();
                return true;
            case R.id.action_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        boolean isAboutShow = false;
        for (Fragment fragment : fragments) {
            if (fragment instanceof AboutFragment && fragment.isVisible()) {
                isAboutShow = true;
            }
        }
        if (!isAboutShow) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_1, new AboutFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}

/*
child-фрагменты ДОБАВИТЬ!!!
1. Подумайте о функционале вашего приложения заметок. Какие экраны там могут быть, помимо основного со списком заметок?
Как можно использовать меню и боковое меню в вашем приложении?
Не обязательно сразу пытаться реализовать весь этот функционал, достаточно создать макеты и структуру, а реализацию пока заменить на заглушки.
Используйте подход Single Activity для отображения экранов.
2. В качестве примера: на главном экране приложения у вас список всех заметок, при нажатии на заметку открывается экран с этой заметкой.
В меню главного экрана у вас есть иконка поиска по заметкам и сортировка. В меню «Заметки» у вас есть иконки «Переслать» (или «Поделиться»), «Добавить ссылку или фотографию к заметке».
3. Создайте боковое навигационное меню для своего приложения и добавьте туда хотя бы один экран, например «Настройки» или «О приложении».
4. * Создайте полноценный заголовок для NavigationDrawer’а. К примеру, аватарка пользователя, его имя и какая-то дополнительная информация.
5. * Изучите context menu
 */

/*
    1. Разберитесь в различиях между serializable и parcelable;
    2. Подумайте о функционале вашего приложения заметок. Какие экраны там могут быть, помимо основного со списком заметок?
    Не обязательно сразу пытаться реализовать весь этот функционал, достаточно создать макеты и структуру,
    а реализацию пока заменить на заглушки или всплывающие уведомления (Toast).
    Используйте подход Single Activity для отображения экранов.
    В качестве примера: на главном экране приложения у вас список всех заметок, при нажатии
    на заметку открывается экран с этой заметкой.
3. * Сделайте UI более сложным, создайте начальный экран приложения с переходами на список
городов, настройки, сведения о приложении. Добавьте в свое приложение child-фрагменты. ДОБАВИТЬ!!!
 */

/*
С этого урока мы начинаем разработку приложения с заметками.
        1. Почитайте документацию методов requireActivity(), requireContext(), getActivity(), getContext() и объясните разницу между ними;
        2. Создайте класс данных со структурой заметок: название заметки, описание заметки, дата создания и т. п.
        3. Создайте фрагмент для вывода этих данных.
        4. Встройте этот фрагмент в активити. У вас должен получиться экран с заметками, который мы будем улучшать с каждым новым уроком.
        5. Добавьте фрагмент, в котором открывается заметка. По аналогии с примером из урока:
        если нажать на элемент списка в портретной ориентации — открывается новое окно,
        если нажать в ландшафтной — окно открывается рядом.
6. * Разберитесь, как можно сделать, и сделайте корректировку даты создания при помощи DatePicker.
 */