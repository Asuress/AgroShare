<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="4">
        <v-card class="elevation-2">
          <v-card-title>
            <v-avatar size="80" class="mr-3">
              <img :src="user.avatar" alt="User Avatar">
            </v-avatar>
            <div>
              <h2 class="font-weight-bold">{{ user.name }}</h2>
              <v-card-subtitle>{{ user.email }}</v-card-subtitle>
            </div>
          </v-card-title>
          <v-card-subtitle class="py-2">{{ user.bio }}</v-card-subtitle>
          <v-card-actions>
            <v-btn color="primary" @click="editProfile">Edit Profile</v-btn>
            <v-btn color="secondary" @click="changePassword">Change Password</v-btn>
          </v-card-actions>
        </v-card>

        <v-card class="elevation-2 mt-4">
          <v-card-title class="headline">Statistics</v-card-title>
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Active Announcements</v-list-item-title>
                <v-list-item-subtitle>{{ user.activeAnnouncementsCount }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Profile Views</v-list-item-title>
                <v-list-item-subtitle>{{ user.profileViews }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>

      <v-col cols="12" md="8">
        <v-card class="elevation-2">
          <v-card-title class="headline">My Announcements</v-card-title>
          <v-list>
            <v-list-item-group>
              <v-list-item
                v-for="announcement in user.announcements"
                :key="announcement.id"
                class="announcement-item"
              >
                <v-list-item-content>
                  <v-list-item-title class="font-weight-medium">{{ announcement.title }}</v-list-item-title>
                  <v-list-item-subtitle>{{ announcement.description }}</v-list-item-subtitle>
                </v-list-item-content>
                <v-list-item-action>
                  <v-btn color="secondary" @click="viewAnnouncement(announcement.id)">View</v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>

        <v-card class="elevation-2 mt-4">
          <v-card-title class="headline">Draft Announcements</v-card-title>
          <v-list>
            <v-list-item
              v-for="draft in user.drafts"
              :key="draft.id"
              class="announcement-item"
            >
              <v-list-item-content>
                <v-list-item-title class="font-weight-medium">{{ draft.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ draft.description }}</v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action>
                <v-btn color="secondary" @click="editDraft(draft.id)">Edit</v-btn>
              </v-list-item-action>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "Profile",
  data() {
    return {
      user: {
        avatar: 'https://www.example.com/avatar.jpg',
        name: 'John Doe',
        email: 'john.doe@example.com',
        bio: 'Software Developer with 5 years of experience in web development.',
        activeAnnouncementsCount: 5,
        profileViews: 123,
        announcements: [
          { id: 1, title: 'First Announcement', description: 'This is the first announcement.' },
          { id: 2, title: 'Second Announcement', description: 'This is the second announcement.' },
        ],
        drafts: [
          { id: 3, title: 'Draft Announcement', description: 'This is a draft announcement.' },
        ],
      },
    };
  },
  methods: {
    editProfile() {
      // Logic to edit profile
    },
    changePassword() {
      // Logic to change password
    },
    viewAnnouncement(id) {
      // Logic to view announcement details
    },
    editDraft(id) {
      // Logic to edit draft announcement
    },
  },
};
</script>

<style scoped>
.v-card {
  margin-bottom: 20px;
  border-radius: 8px;
  overflow: hidden;
}

.v-card-title h2 {
  margin: 0;
}

.announcement-item {
  border-bottom: 1px solid #e0e0e0;
}

.v-btn {
  text-transform: none;
}

.v-card-title,
.v-card-subtitle {
  margin: 0;
}

.v-card-subtitle {
  color: #6c757d;
}

.headline {
  font-size: 1.5rem;
  font-weight: 500;
}

.v-list-item {
  padding: 10px;
}
</style>
