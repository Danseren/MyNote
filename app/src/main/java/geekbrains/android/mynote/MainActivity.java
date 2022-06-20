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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static IDataSource dataSource = new DataSource();
    protected static ArrayList<MyNote> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] my_notes = getResources().getStringArray(R.array.my_notes);
//        MyNote myNote[] = new MyNote[my_notes.length];

//        Toast.makeText(MainActivity.this, my_notes[1], Toast.LENGTH_SHORT).show();

//        for (int i = 0; i < my_notes.length; i++) {
//
//            myNote[i] = new MyNote();
//            myNote[i].setName(my_notes[i].substring(0, 5));
//            myNote[i].setNoteBody(my_notes[i]);
//            //Toast.makeText(MainActivity.this, myNote[i].toString(), Toast.LENGTH_LONG).show();
//        }

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
1. Приложение SharedPreferences всё ещё работает некорректно: если добавить в него несколько заметок, то все они называются одинаково.
Более того, если вы открываете приложение с уже созданными заметками, то при добавлении новой заметки, старые заметки удаляются из списка.
Исправьте эту ситуацию.
2. Сохраняйте список заметок в своём приложении заметок.
3. * Обеспечьте хранение данных приложения через Firestore.
4. * Организуйте аутентификацию пользователя через Google.
5. * Изучите DataStore и замените SharedPreferences.
 */


/*
1. Сделайте фрагмент добавления и редактирования данных, если вы ещё не сделали его.
2. Сделайте навигацию между фрагментами, также организуйте обмен данными между фрагментами.
3. Создайте контекстное меню для изменения и удаления заметок.
4. * Изучите, каким образом можно вызывать DatePicker в виде диалогового окна.
Создайте текстовое поле, при нажатии на которое вызывалось бы диалоговое окно с DatePicker.
 */

/*
Двойной клик, выход из приложения
1. Создайте список ваших заметок.
2. Создайте карточку для элемента списка.
3. Класс данных, созданный на шестом уроке, используйте для заполнения карточки списка.
4. * Создайте фрагмент для редактирования данных в конкретной карточке. Этот фрагмент пока можно вызвать через основное меню.
 */

/*
1. Используйте уведомления или диалоговые окна в своем приложении.
К примеру, перед выходом из приложения уточните у пользователя в диалоговом окне, действительно ли он хочет это сделать. !Двойное нажатие на Назад - диалоговое окно на закрытие!
И отображайте Toast при закрытии приложения.
2. * Возвращайте данные из диалога в активити через интерфейс, но не передавая интерфейс через отдельный метод фрагмента, а приводя контекст к типу этого интерфейса.
ВАЖНО. В ДЗ со звездочкой не надо приводить контекст ни к какому интерфейсу, работате через интерфейсы как на уроке!
 */

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