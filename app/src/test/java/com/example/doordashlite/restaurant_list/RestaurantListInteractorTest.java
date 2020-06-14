package com.example.doordashlite.restaurant_list;

import android.content.Context;
import android.content.Intent;

import com.example.doordashlite.location.DoorDashLocationManager;
import com.example.doordashlite.model.Restaurant;
import com.example.doordashlite.network.NetworkClient;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListAdapter;
import com.example.doordashlite.restaurant_list.full_state.RestaurantListFullStateView;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Response;

import static com.example.doordashlite.restaurant_list.RestaurantListInteractor.ID_EXTRA;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class RestaurantListInteractorTest {

    @Mock Restaurant restaurant;
    @Mock DoorDashLocationManager locationManager;
    @Mock RestaurantListInteractor.RestaurantListPresenter presenter;
    @Mock NetworkClient networkClient;
    @Mock RestaurantListFullStateView fullStateView;
    @Mock RestaurantListAdapter adapter;
    RestaurantListInteractor interactor;
    BehaviorSubject<Optional<Response<List<Restaurant>>>> clientSubject = BehaviorSubject.create();

    @Before
    public void setup() {
        initMocks(this);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        interactor = new RestaurantListInteractor();
        interactor.presenter = presenter;
        interactor.networkClient = networkClient;
        interactor.restaurantListView = fullStateView;
        interactor.locationManager = locationManager;
        interactor.adapter = adapter;
        interactor.context = RuntimeEnvironment.application;
        when(networkClient.getRestaurants(anyDouble(), anyDouble())).thenReturn(clientSubject);
    }

    @Test
    public void onBind_whenNetworkReturnList_shouldLoadFullStateView() {
        interactor.bind();
        clientSubject.onNext(Optional.of(Response.success(ImmutableList.of(restaurant))));
        verify(presenter).showFullState(fullStateView);
        verify(fullStateView).setAdapter(adapter);
        verify(adapter).setRestaurants(ImmutableList.of(restaurant));
    }

    @Test
    public void onItemClick_whenGivenId_shouldLoadDetail() {
        when(restaurant.getId()).thenReturn("abc");
        Intent intent = interactor.onItemClicked(restaurant);
        assertTrue(intent.getStringExtra(ID_EXTRA).equals("abc"));
    }
}
