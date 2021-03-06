# Generated by Django 3.0.4 on 2020-03-29 03:51

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='MasterChallenge',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('description', models.CharField(max_length=512)),
                ('prize', models.CharField(max_length=100)),
                ('daily_point_limit', models.IntegerField()),
                ('team_size_limit', models.IntegerField()),
                ('number_of_days', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='MasterTask',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('description', models.CharField(max_length=512)),
                ('verification_url', models.CharField(max_length=512)),
                ('point_value', models.IntegerField()),
                ('challenge', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='master_tasks', to='gamify.MasterChallenge')),
            ],
        ),
        migrations.CreateModel(
            name='Team',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('join_code', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('email', models.CharField(max_length=100)),
                ('team_leader', models.BooleanField(default=False)),
                ('team', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='members', to='gamify.Team')),
            ],
        ),
        migrations.CreateModel(
            name='TeamScore',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('score', models.IntegerField()),
                ('team', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='gamify.Team')),
            ],
        ),
        migrations.CreateModel(
            name='TaskInstance',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.DateField(auto_now_add=True)),
                ('completed', models.BooleanField()),
                ('task', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='instances', to='gamify.MasterTask')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='tasks', to='gamify.User')),
            ],
        ),
        migrations.CreateModel(
            name='ChallengeInstance',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('start_time', models.DateTimeField()),
                ('challenge', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='challenge_instances', to='gamify.MasterChallenge')),
                ('scores', models.ManyToManyField(to='gamify.TeamScore')),
                ('teams', models.ManyToManyField(to='gamify.Team')),
            ],
        ),
    ]
