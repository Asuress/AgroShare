<template>
  <v-container>
    <v-progress-circular
      v-if="loading"
      indeterminate
      color="primary"
    ></v-progress-circular>
    <v-row>
      <v-col cols="12" md="4">
        <v-card class="elevation-1 rounded-lg px-4 py-6 text-center">
          <v-avatar size="100" class="mx-auto mb-4">
            <img :src="avatarPreview || user.avatar" alt="Аватар пользователя" class="rounded-circle">
          </v-avatar>

          <!-- Кнопка для выбора нового изображения -->
          <v-btn color="primary" class="mb-2" block rounded @click="triggerAvatarUpload">Изменить аватар</v-btn>
          <input type="file" ref="avatarInput" @change="onAvatarChange" accept="image/*" style="display: none;"/>

          <h2 class="mb-2 font-weight-medium text-dark">{{ user.name }}</h2>
          <p class="text-muted">{{ user.email }}</p>
          <p class="text-muted mb-4">{{ user.bio }}</p>

          <v-btn color="primary" class="mb-2" block rounded @click="editProfile">Редактировать профиль</v-btn>
          <v-btn color="secondary" block rounded @click="changePassword">Сменить пароль</v-btn>
        </v-card>
      </v-col>

      <v-col cols="12" md="8">
        <v-card class="elevation-1 rounded-lg px-4 py-4">
          <v-card-title class="text-h6 text-dark">Мои объявления</v-card-title>
          <v-divider class="my-2"></v-divider>
          <v-list dense v-if="!!user.announcements">
            <v-list-item
              v-for="announcement in user.announcements"
              :key="announcement.id"
            >
              <v-list-item-content>
                <v-list-item-title>{{ announcement.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ announcement.description }}</v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn color="primary" small @click="viewAnnouncement(announcement.id)">Просмотреть</v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
          <p v-else>Нет доступных публикаций</p>

        </v-card>
      </v-col>
    </v-row>

    <!-- Диалог для редактирования профиля -->
    <v-dialog v-model="profileDialog" persistent max-width="500px">
      <v-card>
        <v-card-title class="headline">Редактирование профиля</v-card-title>
        <v-card-text>
          <v-text-field label="Имя" v-model="userEdit.name"></v-text-field>
          <v-text-field label="Email" v-model="userEdit.email"></v-text-field>
          <!--          <v-textarea label="Биография" v-model="user.bio"></v-textarea>-->
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="profileDialog = false">Отмена</v-btn>
          <v-btn color="blue darken-1" text @click="saveProfile">Сохранить</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- Диалог для смены пароля -->
    <v-dialog v-model="passwordDialog" persistent max-width="500px">
      <v-card>
        <v-card-title class="headline">Смена пароля</v-card-title>
        <v-card-text>
          <v-text-field label="Текущий пароль" v-model="currentPassword" type="password"></v-text-field>
          <v-text-field label="Новый пароль" v-model="newPassword" type="password"></v-text-field>
          <v-text-field label="Подтвердите новый пароль" v-model="confirmPassword" type="password"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="passwordDialog = false">Отмена</v-btn>
          <v-btn color="blue darken-1" text @click="savePassword">Сохранить</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
import UserHelper from "@/utils/user-helper";
import PublicationHelper from "@/utils/publications/publication-helper";

export default {
  name: "Profile",
  data() {
    return {
      loading: false,
      userId: this.$route.params.id,
      user: {
        id: this.$route.params.id,
        avatar: null, // URL текущего аватара
        name: null,
        email: null,
        bio: null,
        announcements: [],
        drafts: [],
      },
      announcements: [],
      userEdit: {
        id: this.$route.params.id,
        avatar: null,
        name: null,
        email: null,
        bio: null,
      },
      avatarPreview: '', // Для предварительного просмотра аватара
      profileDialog: false,
      passwordDialog: false,
      currentPassword: '',
      newPassword: '',
      confirmPassword: '',
    };
  },
  methods: {
    // Открыть диалог редактирования профиля
    editProfile() {
      this.profileDialog = true;
    },
    // Открыть диалог смены пароля
    changePassword() {

    },
    // Логика сохранения профиля
    saveProfile() {
      this.profileDialog = false;
      // Здесь можно добавить логику для сохранения профиля, например, через API-запрос
      console.log("Профиль сохранён:", this.user);
    },
    // Логика смены пароля
    savePassword() {
      if (this.newPassword !== this.confirmPassword) {
        alert("Пароли не совпадают!");
        return;
      }
      this.passwordDialog = false;
      // Логика смены пароля, например, отправка запроса на сервер
      console.log("Пароль сменён");
    },
    // Логика просмотра объявления
    viewAnnouncement(id) {
      this.$router.push(`/publications/publication/${id}`);
    },
    // Логика редактирования черновика
    editDraft(id) {
      alert(`Редактирование черновика с ID: ${id}`);
      // Переход на страницу редактирования черновика
    },
    // Триггер для загрузки изображения
    triggerAvatarUpload() {
      this.$refs.avatarInput.click();
    },
    // Изменение аватара
    onAvatarChange(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.avatarPreview = e.target.result; // Показываем превью аватара
        };
        reader.readAsDataURL(file);
      }
    },
    async fetchData() {
      this.loading = true; // Устанавливаем флаг загрузки
      try {
        console.log("params.id", this.$route.params.id);
        console.log("is integer", !!this.$route.params.id);
        this.user.id = this.$route.params.id;
        if (!!this.user.id) {
          UserHelper.getUser(this.user.id).then(response => {
            console.log(response.data);
            this.user = JSON.parse(JSON.stringify(response.data));
            this.userEdit = this.user;
            console.log("user 196", this.user);
            PublicationHelper.getPublicationsByUserId(this.$route.params.id).then(response => {
              this.user.announcements = response.data;
              this.announcements = response.data;
              console.log("publications", this.user.announcements);
              console.log("publications2", this.announcements);
              console.log("user publ", this.user);
            });
          });
        } else {
          this.$router.go(-1);
        }
      } catch (error) {
        console.error('Ошибка при загрузке данных:', error);
      } finally {
        this.loading = false; // Сбрасываем флаг загрузки
      }
    },
  },
  created() {
    this.fetchData();
  }
};
</script>

<style scoped>
.v-card {
  margin-bottom: 20px;
  border-radius: 8px;
  overflow: hidden;
}

.v-avatar {
  border: 2px solid #ddd;
}

.v-btn {
  text-transform: none;
}

.v-card-title,
.v-card-subtitle {
  margin: 0;
}

.v-list-item {
  padding: 10px;
}

.announcement-item {
  border-bottom: 1px solid #e0e0e0;
}

.headline {
  font-size: 1.5rem;
  font-weight: 500;
}

.v-list-item-title {
  font-size: 1.1rem;
}

.v-list-item-subtitle {
  font-size: 0.9rem;
  color: #6c757d;
}
</style>
